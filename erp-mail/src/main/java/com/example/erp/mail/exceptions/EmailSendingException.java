package com.example.erp.mail.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when there is an error sending an email.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailSendingException extends AppException {
    public EmailSendingException(String message) {
        super(message);
    }
}
