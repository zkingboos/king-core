package io.king.core.api.exception.service;

import java.util.NoSuchElementException;

public final class NoSuchServiceRegistryException extends NoSuchElementException {

    private static final String EXCEPTION_MESSAGE = "No such service (%s) has been found on registry.";

    public NoSuchServiceRegistryException(Class<?> service) {
        super(
          String.format(
            EXCEPTION_MESSAGE,
            service.getSimpleName()
          )
        );
    }
}
