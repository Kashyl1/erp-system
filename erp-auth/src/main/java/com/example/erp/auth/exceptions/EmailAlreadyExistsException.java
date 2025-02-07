package com.example.erp.auth.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an email address is already associated with an existing account.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends AppException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
