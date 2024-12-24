package com.tuantran.jwt_authentication.exception.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
        errorDetail.setTitle("Unauthorized");

        // Set instance as a URI
        try {
            URI instanceURI = new URI(request.getRequestURI());
            errorDetail.setInstance(instanceURI);
        } catch (Exception e) {
            errorDetail.setInstance(URI.create("about:blank"));
        }

        // Add custom properties
        errorDetail.setDetail(exception.getMessage());
        errorDetail.setProperty("timestamp", Instant.now().toString());
        errorDetail.setProperty("description", exception.getMessage());

        // Convert ProblemDetail to JSON using ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorDetail);

        // Set response headers and status
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Write JSON response
        try (var writer = response.getWriter()) {
            writer.write(jsonResponse);
        }
    }
}