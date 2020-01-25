package com.smoothstack.december.orchestrationservice.exception;

public class CriticalDatabaseErrorException extends RuntimeException {

    public CriticalDatabaseErrorException(String message) {
        super(message);
    }

}
