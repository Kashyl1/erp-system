package com.example.erp.auth.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an operation requires an account to be verified, but it is not.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountNotVerifiedException extends AppException {
    public AccountNotVerifiedException(String message) {
        super(message);
    }
}
