package io.king.core.api.exception.service;

public final class RedundantServiceException extends StackOverflowError {

    private final static String REDUNDANT_SERVICE_IMPORT = "Service (%s) and subservice (%s) are the same.";

    public RedundantServiceException(Class<?> serviceType, Class<?> importServiceType) {
        super(
          String.format(
            REDUNDANT_SERVICE_IMPORT,
            serviceType.getSimpleName(),
            importServiceType.getSimpleName()
          )
        );
    }
}
