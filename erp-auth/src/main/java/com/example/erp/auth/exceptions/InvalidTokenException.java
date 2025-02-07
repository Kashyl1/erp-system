package com.example.erp.auth.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an invalid token is provided, such as for password reset or email verification.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTokenException extends AppException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
