package com.example.erp.mail.exceptions;

import com.example.erp.common.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class ResendEmailCooldownException extends AppException {
    public ResendEmailCooldownException(String message) {
        super(message);
    }
}
