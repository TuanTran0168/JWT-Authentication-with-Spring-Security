package com.tuantran.jwt_authentication.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
    public ApiException(String message,HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
