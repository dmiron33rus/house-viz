package com.houseviz.houseviz.auth;

import com.houseviz.houseviz.common.exception.AppException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyUsedException extends AppException {
    public EmailAlreadyUsedException(String email) {
        super(HttpStatus.CONFLICT, "EMAIL_ALREADY_USED", "Email already in use: " + email);
    }
}
