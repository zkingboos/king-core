package io.king.core.api.cycle;

import io.king.core.provider.module.ModuleObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Context of life-cycle class
 * This class is responsible to "export" api to modules
 */
public interface LifeContext {

    /**
     * Instance plugin of core
     *
     * @return plugin's core
     */
    JavaPlugin getCorePlugin();

    /**
     * Logger of core
     *
     * @return logger's core
     */
    Logger getLogger();

    /**
     * Object of module
     *
     * @return instance of module object
     */
    ModuleObject getModuleObject();

    /**
     * Shortcut to get services from @ServiceManager
     *
     * @param registration class of type
     * @param <T>          type of registration service
     * @return instance of registration service
     */
    <T> T getService(Class<?> registration);

    /**
     * Register services for dependency injection
     *
     * @param services instance of services
     */
    void registerServices(Object... services);

    /**
     * Verify if the life context is a module
     *
     * @return result
     */
    boolean isModule();

    /**
     * Register the listener for lifecycle
     *
     * @param clazz type of class
     * @param consumer accept
     * @param <T> type of event
     */
    <T> void registerEvent(Class<T> clazz, Consumer<T> consumer);
}
