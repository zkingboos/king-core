/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.provider.bootstrap;

import io.king.module.common.io.FileConverter;
import io.king.module.provider.loader.ModuleClassLoader;
import io.king.module.provider.loader.ModuleModelImpl;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;

public final class CorePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final File baseModulePath = new File(getDataFolder(), "/modules");

        final URL[] urlsFilesContent = FileConverter.convertFilesFromFolderToUrl(baseModulePath);
        final ClassLoader classLoader = new ModuleClassLoader(urlsFilesContent);

        final ModuleModelImpl moduleModel = new ModuleModelImpl(baseModulePath, classLoader);
        moduleModel.loadModulesFromBasePath();
    }
}
