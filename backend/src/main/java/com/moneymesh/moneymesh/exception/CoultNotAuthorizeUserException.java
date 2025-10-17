package com.moneymesh.moneymesh.exception;

import org.springframework.security.core.AuthenticationException;

public class CoultNotAuthorizeUserException extends AuthenticationException {
    public CoultNotAuthorizeUserException(String msg) {
        super(msg);
    }

    public CoultNotAuthorizeUserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
