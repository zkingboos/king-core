package io.king.core.api.exception;

import java.util.NoSuchElementException;

public final class InjectableException extends NoSuchElementException {

    private final static String EXCEPTION_MESSAGE = "Can´t find type of service registration (%s) in injection of constructor of class (%s) has be no found.";

    public InjectableException(Class<?> injectionClass, Class<?> typeService) {
        super(
          String.format(
            EXCEPTION_MESSAGE,
            typeService.getSimpleName(),
            injectionClass.getSimpleName()
          )
        );
    }
}
