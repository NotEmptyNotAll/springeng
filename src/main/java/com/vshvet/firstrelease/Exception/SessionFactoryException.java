package com.vshvet.firstrelease.Exception;

import org.hibernate.service.spi.ServiceException;

public class SessionFactoryException extends ServiceException {
    public SessionFactoryException() {
        super("connection limit exceeded or data for connection specified incorrectly");
    }
    public SessionFactoryException(String message, Throwable root) {
        super(message, root);
    }


}
