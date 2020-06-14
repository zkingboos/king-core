package io.king.core.provider.service;

import io.king.core.api.service.ServiceEntity;
import io.king.core.api.service.ServiceManager;
import io.king.core.api.service.ServiceType;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ServiceManagerImpl implements ServiceManager {

    private final Map<Class<?>, ServiceEntity<?>> services = new LinkedHashMap<>();

    @Override
    public void registerServiceType(Object service, ServiceType type) {

    }

    @Override
    public void registerServices(Object... services) {
        for (Object service : services) {
            registerService(service);
        }
    }

    @Override
    public <T> ServiceEntity<T> registerService(T service) {
        final ServiceEntity<T> serviceObject = new ServiceEntityImpl<>(service);
        final Class<?> classService = service.getClass();

        services.put(classService, serviceObject);
        return serviceObject;
    }

    @Override
    public ServiceEntity<?> getRegistrationService(Class<?> registration) {
        return services.get(registration);
    }
}
