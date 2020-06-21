package io.king.core.api.di;

/**
 * Used to inject instances of fields annotated with @Inject
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

    /**
     * Inject on constructor @InjectClass
     *
     * @param clazz type of class
     * @return instance of class
     * @throws Exception denied permission constructor access
     */
    Object injectIntoClass(Class<?> clazz) throws Exception;
}
