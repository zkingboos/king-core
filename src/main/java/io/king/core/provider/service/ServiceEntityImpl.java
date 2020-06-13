package io.king.core.provider.service;

import io.king.core.api.service.ServiceEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ServiceEntityImpl<T> implements ServiceEntity<T> {

    @Getter
    private final T service;

    @Override
    public Class<?> getRegistration() {
        return service.getClass();
    }
}
