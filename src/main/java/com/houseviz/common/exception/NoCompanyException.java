package com.houseviz.common.exception;

public class NoCompanyException extends ForbiddenException {
    public NoCompanyException() {
        super("NO_COMPANY", "User is not bound to a company");
    }
}
