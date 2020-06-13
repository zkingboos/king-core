package io.king.core.api.cycle;

import io.king.core.provider.module.ModuleObject;

/**
 * This class is a responsibility to load @Module annotations
 * The all life-cycles are be loaded here
 */
public interface CycleLoader {

    /**
     * Used to resolve life-cycles from @ServiceManager
     * @param moduleObject is module file converted to POO
     * @throws Exception anything can break here
     */
    void resolveCycle(ModuleObject moduleObject) throws Exception;

    /**
     * Used to initialize module instance "commands or imports"
     * @param clazz type of module
     * @return instance of module
     * @throws Exception no such element found on service manager
     */
    Object initialize(Class<?> clazz) throws Exception;

    /**
     * Notify the life cycles that module has been initialized
     * @param lifeCycle life of cycle
     */
    void notifyModule(LifeCycle lifeCycle);

    /**
     * Pre notify the life cycles that module has been fired
     * @param lifeCycle life of cycle
     */
    void preNotifyModule(LifeCycle lifeCycle);

    /**
     * Initialize life of cycle
     * @param moduleInstance the instance of loaded module
     * @return the instance of lifecycle
     */
    LifeCycle initializeLife(Object moduleInstance);
}
