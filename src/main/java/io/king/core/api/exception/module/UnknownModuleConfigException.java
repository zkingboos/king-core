package io.king.core.api.exception.module;

import java.util.NoSuchElementException;

public final class UnknownModuleConfigException extends NoSuchElementException {

    private static final String EXCEPTION_MESSAGE = "The config type class (%s) for module (%s) should be moduleConfig module type.";

    public UnknownModuleConfigException(Class<?> moduleClass, Class<?> moduleConfig) {
        super(
          String.format(
            EXCEPTION_MESSAGE,
            moduleConfig.getSimpleName(),
            moduleClass.getSimpleName()
          )
        );
    }
}
