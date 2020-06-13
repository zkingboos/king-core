package io.king.core.api.exception.module;

import java.util.NoSuchElementException;

public final class NoSuchModuleException extends NoSuchElementException {

    private final static String EXCEPTION_MESSAGE = "Module (%s) is not loaded.";

    public NoSuchModuleException(Class<?> moduleType) {
        super(
          String.format(
            EXCEPTION_MESSAGE,
            moduleType.getSimpleName()
          )
        );
    }
}
