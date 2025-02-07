package com.example.erp.auth.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when a provided password does not meet security requirements.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends AppException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
