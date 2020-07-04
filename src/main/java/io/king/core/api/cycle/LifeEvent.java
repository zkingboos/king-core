package io.king.core.api.cycle;

import java.util.function.Consumer;

public interface LifeEvent {

    /**
     * Register the listener for lifecycle
     *
     * @param clazz type of class
     * @param consumer accept
     * @param <T> type of event
     */
    <T> void registerEvent(Class<T> clazz, Consumer<T> consumer);

    /**
     * Notify the registered listeners
     *
     * @param payload instance of paylaod
     * @param <T> type of payload
     */
    <T> void notifyListeners(T payload);
}
