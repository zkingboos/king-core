package io.king.core.api.exception.module;

public final class OverflowSoftDependException extends StackOverflowError {

    private final static String EXCEPTION_MESSAGE = "Overflow on softDepend (%s) at module (%s).";

    public OverflowSoftDependException(Class<?> module, Class<?> softDepend) {
        super(
          String.format(
            EXCEPTION_MESSAGE,
            softDepend.getSimpleName(),
            module.getSimpleName()
          )
        );
    }
}
