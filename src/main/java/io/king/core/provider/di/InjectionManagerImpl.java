package io.king.core.provider.di;

import io.king.core.api.di.Inject;
import io.king.core.api.di.InjectionManager;
import io.king.core.api.service.ServiceEntity;
import io.king.core.api.service.ServiceManager;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

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
            if (service == null) throw new NoSuchElementException();

            final Object serviceObject = service.getService();
            final boolean accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(objectInstance, serviceObject);
            field.setAccessible(accessible);
        }
    }
}
