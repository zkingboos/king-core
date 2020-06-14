package io.king.core.api.module;

/**
 * ModuleContainer is a singleton instance
 * Used to get ModuleManager from each plugin
 */
public interface ModuleContainer {

    /**
     * Register ModuleManager in container module
     *
     * @param clazz   is the module tree class
     * @param manager instance of module manager
     */
    void registerManager(Class<?> clazz, ModuleManager manager);

    /**
     * Get module manager from module tree class
     *
     * @param clazz type of tree class
     * @return instance of module manager tree class
     */
    ModuleManager getModuleManager(Class<?> clazz);
}
