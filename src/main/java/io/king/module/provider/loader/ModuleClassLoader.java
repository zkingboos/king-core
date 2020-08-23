/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.provider.loader;

import io.king.module.provider.bootstrap.CorePlugin;

import java.net.URL;
import java.net.URLClassLoader;

public final class ModuleClassLoader extends URLClassLoader {

    public ModuleClassLoader(URL[] urls) {
        super(
          urls,
          CorePlugin.class.getClassLoader()
        );
    }
}
