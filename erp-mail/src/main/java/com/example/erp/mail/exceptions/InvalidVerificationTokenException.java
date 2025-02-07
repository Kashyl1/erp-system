package com.example.erp.mail.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an email verification token is invalid or expired.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidVerificationTokenException extends AppException {
    public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
