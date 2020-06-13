package io.king.core.api.service;

/**
 * Entity of service, where you can obtain the service and registration service
 *
 * @param <T> type of service
 */
public interface ServiceEntity<T> {

    /**
     * @return Returns type of registration service
     */
    Class<?> getRegistration();

    /**
     * @return returns service
     */
    T getService();
}
