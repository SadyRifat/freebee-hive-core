package com.freebee.hive.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ComplianceException extends Exception {
    protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public ComplianceException(String message) {
        super(message);
    }

    public ComplianceException(String message, Throwable cause) {
        super(message, cause);
    }
}
