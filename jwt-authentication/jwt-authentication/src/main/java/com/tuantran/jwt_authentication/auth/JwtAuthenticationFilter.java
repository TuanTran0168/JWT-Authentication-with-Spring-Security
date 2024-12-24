package com.tuantran.jwt_authentication.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tuantran.jwt_authentication.exception.JwtAuthenticationException;
import com.tuantran.jwt_authentication.service.impl.JwtService;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
            String jwtToken = authHeader.substring("Bearer ".length());
            String email;
            try {
                email = jwtService.getEmail(jwtToken);
                Claims claims = jwtService.getClaims(jwtToken);
                String role = claims.get("role", String.class);
                SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role);
                if (email != null) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(authorities)
                    );
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            } catch (SignatureException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                authenticationFailureHandler.onAuthenticationFailure(request, response, new JwtAuthenticationException("token invalid"));
                return; // Stop further processing of the filter chain
            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                authenticationFailureHandler.onAuthenticationFailure(request, response, new JwtAuthenticationException("jwt expired"));
                return; // Stop further processing of the filter chain
            }
        filterChain.doFilter(request, response);
    }


}