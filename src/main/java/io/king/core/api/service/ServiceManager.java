package io.king.core.api.service;

/**
 * Manager of services
 * This is used to share instance with others modules
 */
public interface ServiceManager {

    /**
     * Register services into service manager
     *
     * @param services instance of services
     */
    void registerServices(Object... services);

    /**
     * Register single service into service manager
     *
     * @param service instance of service
     * @param <T>     type of instance service
     * @return the entity of type
     */
    <T> ServiceEntity<T> registerService(T service);

    /**
     * Gets the registration of service in service manager
     *
     * @param registration class of registration
     * @return returns the entity type
     */
    ServiceEntity<?> getRegistrationService(Class<?> registration);

}