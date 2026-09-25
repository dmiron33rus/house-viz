package com.houseviz.auth;

import com.houseviz.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyUsedException extends AppException {
    public EmailAlreadyUsedException(String email) {
        super(HttpStatus.CONFLICT, "EMAIL_ALREADY_USED", "Email already in use: " + email);
    }
}
