package com.houseviz.common.exception;

import org.springframework.http.HttpStatus;

public abstract class NotFoundException extends AppException {
    protected NotFoundException(String code, String message) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
}
