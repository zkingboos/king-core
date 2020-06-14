package io.king.core.api.di;

/**
 * Used to inject instances of fields annoted with @Inject
 */
public interface InjectionManager {

    /**
     * Inject dependencies into service
     *
     * @param objectInstance instance of object service
     * @param clazz          type of service
     * @throws IllegalAccessException cant set field value
     */
    void injectIntoService(Object objectInstance, Class<?> clazz) throws IllegalAccessException;
}
