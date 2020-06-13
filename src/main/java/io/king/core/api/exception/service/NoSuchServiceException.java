package io.king.core.api.exception.service;

import java.util.NoSuchElementException;

public final class NoSuchServiceException extends NoSuchElementException {

    private final static String EXCEPTION_MESSAGE = "Service (%s) should be annotated with @Service";

    public NoSuchServiceException(Class<?> serviceType) {
        super(
          String.format(
            EXCEPTION_MESSAGE,
            serviceType.getSimpleName()
          )
        );
    }
}
