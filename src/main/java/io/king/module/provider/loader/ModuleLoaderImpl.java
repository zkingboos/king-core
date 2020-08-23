/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.provider.loader;

import io.king.module.api.module.Configuration;
import io.king.module.api.module.Module;
import io.king.module.common.exception.module.ModuleConfigInitialized;
import io.king.module.common.exception.module.ModuleConfigurationTypeException;
import io.king.module.common.exception.module.NoSuchModuleMainClass;
import io.king.module.provider.module.ModuleArchive;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@RequiredArgsConstructor
public final class ModuleLoaderImpl {

    private final static Class<Module> MODULE_CLASS = Module.class;
    private final static Class<Configuration> CONFIGURATION_CLASS = Configuration.class;

    private final ClassLoader classLoader;

    public ModuleArchive loadModule(@NonNull File moduleFilePath) throws Exception {
        try (JarFile moduleFile = new JarFile(moduleFilePath)) {
            return loadContentModule(moduleFile);
        }
    }

    public ModuleArchive loadContentModule(@NonNull JarFile jarFile) throws Exception {
        final ModuleArchive moduleArchive = new ModuleArchive();
        moduleArchive.setModuleName(jarFile.getName());

        final Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            final JarEntry entry = entries.nextElement();

            String name = entry.getName().replace("/", ".");
            if (!name.endsWith(".class")) continue;

            name = name.substring(0, name.length() - 6);
            final Class<?> entryContentClass = loadAndGetClass(name);

            final Module annotation = entryContentClass.getAnnotation(MODULE_CLASS);
            if (annotation != null) {
                moduleArchive.setModuleMainClass(entryContentClass);
                moduleArchive.setModuleAnnotation(annotation);
            }
        }

        if (moduleArchive.getModuleMainClass() == null) {
            throw new NoSuchModuleMainClass(jarFile.getName());
        }

        loadConfigurationModule(moduleArchive);
        return moduleArchive;
    }

    public Class<?> loadAndGetClass(@NonNull String packageClass) throws ClassNotFoundException {
        return Class.forName(
          packageClass,
          true,
          classLoader
        );
    }

    public void loadConfigurationModule(@NonNull ModuleArchive moduleArchive) throws Exception {
        if (moduleArchive.getModuleConfiguration() != null) {
            throw new ModuleConfigInitialized(moduleArchive);
        }

        final Module module = moduleArchive.getModuleAnnotation();
        final Class<? extends Configuration> moduleConfigurationClass = module.config();

        final boolean assignable = CONFIGURATION_CLASS.isAssignableFrom(moduleConfigurationClass);
        if (!assignable) {
            throw new ModuleConfigurationTypeException(moduleArchive);
        }

        final Configuration configuration = moduleConfigurationClass.newInstance();
        moduleArchive.setModuleConfiguration(configuration);
    }
}
