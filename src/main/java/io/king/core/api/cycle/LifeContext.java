package io.king.core.api.cycle;

import io.king.core.api.service.Service;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Context of life-cycle class
 * This class is responsible to "export" api to modules
 */
@Service
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
}
