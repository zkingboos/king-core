package io.king.core.api.cycle;

import io.king.core.api.di.InjectionManager;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.module.ModuleObject;

import java.io.File;

/**
 * This class is a responsibility to load @Module annotations
 * The all life-cycles are be loaded here
 */
public interface CycleLoader {

    /**
     * Managers of services
     *
     * @return instance of service manager
     */
    ServiceManager getServiceManager();

    /**
     * Injection manager of DI
     *
     * @return instance of injection
     */
    InjectionManager getInjectionManager();

    /**
     * Used to resolve life-cycles from @ServiceManager
     *
     * @param moduleObject is module file converted to POO
     * @throws Exception anything can break here
     */
    void resolveCycle(ModuleObject moduleObject) throws Exception;

    /**
     * Used to initialize module instance "commands or imports"
     *
     * @param clazz type of module
     * @return instance of module
     * @throws Exception no such element found on service manager
     */
    Object initialize(Class<?> clazz) throws Exception;

    /**
     * Notify the life cycles that module has been initialized
     *
     * @param lifeCycle   life of cycle
     * @param lifeContext instance of life context
     */
    void notifyModule(LifeCycle lifeCycle, LifeContext lifeContext);

    /**
     * Pre notify the life cycles that module has been fired
     *
     * @param lifeCycle   life of cycle
     * @param lifeContext instance of life context
     */
    void preNotifyModule(LifeCycle lifeCycle, LifeContext lifeContext);

    /**
     * Dispose module
     *
     * @param objectModule instance of module
     * @param lifeContext  instance of life context
     */
    void notifyDisposeModule(ModuleObject objectModule, LifeContext lifeContext);

    /**
     * Initialize life of cycle
     *
     * @param moduleInstance the instance of loaded module
     * @return the instance of lifecycle
     */
    LifeCycle initializeLife(Object moduleInstance);

    /**
     * Resolve life context
     *
     * @param moduleObject instance of module object
     * @return instance of life context
     */
    LifeContext resolveContext(ModuleObject moduleObject);

    /**
     * Set module directory path
     *
     * @param directory instance of path
     */
    void setModuleDirectory(File directory);

    /**
     * Kill the lifecycle
     *
     * @param moduleObject instance of module object
     * @param lifeContext  context of the life
     */
    void removalServices(ModuleObject moduleObject, LifeContext lifeContext);
}
