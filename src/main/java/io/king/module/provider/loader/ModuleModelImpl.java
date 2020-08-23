/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.provider.loader;

import io.king.module.common.io.ModuleFilter;
import io.king.module.provider.module.ModuleArchive;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;

@RequiredArgsConstructor
public final class ModuleModelImpl {

    private final static FileFilter MODULE_FILE_FILTER = new ModuleFilter();

    private final File moduleBasePath;
    private final ModuleLoaderImpl moduleLoader;

    public ModuleModelImpl(File moduleBasePath, ClassLoader classLoader) {
        this.moduleBasePath = moduleBasePath;
        this.moduleLoader = new ModuleLoaderImpl(classLoader);
    }

    public void loadModulesFromBasePath() {
        loadModulesFromPath(moduleBasePath);
    }

    public void loadModulesFromPath(File path) {
        try {
            final File[] files = path.listFiles(MODULE_FILE_FILTER);

            for (File file : Objects.requireNonNull(files)) {
                final ModuleArchive moduleArchive = moduleLoader.loadModule(file);
                System.out.println("moduleArchive = " + moduleArchive);
            }
        } catch (Exception $) {
            $.printStackTrace();
        }
    }
}
