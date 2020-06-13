package io.king.core.api.module;

/**
 * This class takes the responsability to load Models Manager
 */
public interface ModuleModel {

    /**
     * Load contents of model manager
     * @return instance of loaded model managers
     * @throws Exception file exception, no such element and more
     */
    ModuleManager load() throws Exception;
}
