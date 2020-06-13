package io.king.core.api.module;

import io.king.core.provider.module.ModuleObject;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Tree of modules
 * Here you can get the all loaded modules
 */
public interface ModuleManager {

    /**
     * Java plugin instance of module tree class
     *
     * @return bukkit java plugin instance
     */
    JavaPlugin getPlugin();

    /**
     * All modules that are loaded
     *
     * @return collection of modules
     */
    List<ModuleObject> getModules();

    /**
     * Start the all lifecycles
     *
     * @return module manager
     * @throws Exception no such elements found
     */
    ModuleManager lifeCycle() throws Exception;

    /**
     * Order modules by module priority
     *
     * @return instance of module manager
     */
    ModuleManager orderByPriority();

    /**
     * Shutdown modules
     */
    void orderShutdown();

    /**
     * Find module object in module manager
     *
     * @param clazz type of registration module
     * @return instance of module
     */
    ModuleObject findModuleByType(Class<?> clazz);

    /**
     * Try to load life
     *
     * @param module instance of module object
     * @throws Exception breaks?
     */
    void tryLoadLife(ModuleObject module) throws Exception;
}
