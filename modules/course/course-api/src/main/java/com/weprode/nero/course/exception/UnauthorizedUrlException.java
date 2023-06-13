package com.weprode.nero.course.exception;

public class UnauthorizedUrlException extends Exception {
    public UnauthorizedUrlException() {
        super();
    }

    public UnauthorizedUrlException(String msg) {
        super(msg);
    }

    public UnauthorizedUrlException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnauthorizedUrlException(Throwable cause) {
        super(cause);
    }
}
