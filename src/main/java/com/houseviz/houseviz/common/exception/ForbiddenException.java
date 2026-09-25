package com.houseviz.houseviz.common.exception;

import org.springframework.http.HttpStatus;

public abstract class ForbiddenException extends AppException {
    protected ForbiddenException(String code, String message) {
        super(HttpStatus.FORBIDDEN, code, message);
    }
}
