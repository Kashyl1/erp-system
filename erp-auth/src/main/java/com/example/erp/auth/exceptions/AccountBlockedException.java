package com.example.erp.auth.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an operation cannot be performed because the account is blocked.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountBlockedException extends AppException {
    public AccountBlockedException(String message) {
        super(message);
    }
}
