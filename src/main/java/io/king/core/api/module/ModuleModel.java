package io.king.core.api.module;

import io.king.core.provider.module.ModuleObject;
import io.king.core.provider.module.ModuleProps;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLClassLoader;

/**
 * This class takes the responsability to load Models Manager
 */
public interface ModuleModel {

    /**
     * Load contents of model manager
     *
     * @return instance of loaded model managers
     * @throws Exception file exception, no such element and more
     */
    ModuleManager load() throws Exception;

    /**
     * Resole url class loader
     *
     * @param moduleFiles array of files
     * @return instance of class loader
     * @throws MalformedURLException url malformed
     */
    URLClassLoader resolveClasses(File[] moduleFiles) throws MalformedURLException;

    /**
     * Load file from url class loader
     *
     * @param file        instance of file
     * @param classLoader instance of class loader
     * @return instance of module object
     * @throws IOException            io error
     * @throws ClassNotFoundException class not found
     */
    ModuleObject loadFile(File file, ClassLoader classLoader) throws Exception;

    /**
     * Get main of module
     *
     * @param file        instance of file
     * @param classLoader classLoader
     * @return main class of module
     * @throws IOException not found file
     */
    ModuleProps loadJarFile(File file, ClassLoader classLoader) throws IOException, Exception;
}
