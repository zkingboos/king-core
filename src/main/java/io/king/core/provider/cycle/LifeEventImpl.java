package io.king.core.provider.cycle;

import io.king.core.api.cycle.LifeEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public final class LifeEventImpl implements LifeEvent {

    private final List<ListenerObject<?>> listenerObjects = new LinkedList<>();

    @Override
    public <T> void registerEvent(Class<T> clazz, Consumer<T> consumer) {
        final ListenerObject<T> listenerObject = new ListenerObject<>(clazz, consumer);
        listenerObjects.add(listenerObject);
    }

    @Override
    public <T> void notifyListeners(T payload) {
        final Class<?> clazz = payload.getClass();
        for (ListenerObject listenerObject : listenerObjects) {
            if (listenerObject.getTypeClass() == clazz) {
                listenerObject.getConsumer().accept(payload);
            }
        }
    }
}
