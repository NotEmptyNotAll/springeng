package com.vshvet.firstrelease.Exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        System.out.printf("element with this parameter{ "+message+" } not found");
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
