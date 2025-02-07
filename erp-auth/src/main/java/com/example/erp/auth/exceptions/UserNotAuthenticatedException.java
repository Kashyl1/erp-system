package com.example.erp.auth.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an operation requires authentication, but the user is not authenticated.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotAuthenticatedException extends AppException {
    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
