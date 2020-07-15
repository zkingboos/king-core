package io.king.core.provider.di;

import io.king.core.api.di.Inject;
import io.king.core.api.di.InjectionManager;
import io.king.core.api.exception.InjectableException;
import io.king.core.api.exception.service.NoSuchServiceRegistryException;
import io.king.core.api.service.ServiceEntity;
import io.king.core.api.service.ServiceManager;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

@RequiredArgsConstructor
public final class InjectionManagerImpl implements InjectionManager {

    private static final Class<Inject> INJECT_CLASS = Inject.class;

    private final ServiceManager serviceManager;

    public void injectIntoService(Object objectInstance, Class<?> clazz) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            final boolean isPresent = field.isAnnotationPresent(INJECT_CLASS);
            if (!isPresent) continue;

            final Class<?> type = field.getType();
            final ServiceEntity<?> service = serviceManager.getRegistrationService(type);
            if (service == null)
                throw new NoSuchServiceRegistryException(type);

            final Object serviceObject = service.getService();

            field.setAccessible(true);
            field.set(objectInstance, serviceObject);
        }
    }

    @Override
    public Object injectIntoClass(Class<?> clazz) throws Exception {

        mainLoop:
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            final int parameterCount = constructor.getParameterCount();

            final Parameter[] parameters = constructor.getParameters();
            final Object[] objects = new Object[parameterCount];

            for (int i = 0; i < parameters.length; i++) {
                final Parameter type = parameters[i];
                if (!type.isAnnotationPresent(INJECT_CLASS)) continue mainLoop;

                final Class<?> typeService = type.getType();
                final ServiceEntity<?> registration = serviceManager.getRegistrationService(
                  typeService
                );

                if (registration == null)
                    throw new InjectableException(clazz, typeService);

                objects[i] = registration.getService();
            }

            final Object objectInstance = constructor.newInstance(objects);
            serviceManager.registerServices(objectInstance);
            return objectInstance;
        }
        return null;
    }
}
