package io.king.core.api.module;

import io.king.core.provider.module.ModuleObject;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Tree of modules
 * Here you can get the all loaded modules
 */
public interface ModuleManager {

    /**
     * Java plugin instance of module tree class
     * @return bukkit java plugin instance
     */
    JavaPlugin getPlugin();

    /**
     * All modules that are loaded
     * @return collection of modules
     */
    List<ModuleObject> getModules();

    /**
     * Start the all lifecycles
     * @return module manager
     * @throws Exception no such elements found
     */
    ModuleManager lifeCycle() throws Exception;
}
