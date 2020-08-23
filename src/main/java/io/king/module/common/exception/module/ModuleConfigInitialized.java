/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.common.exception.module;

import io.king.module.provider.module.ModuleArchive;

public final class ModuleConfigInitialized extends IllegalStateException {

    public ModuleConfigInitialized(ModuleArchive moduleArchive) {
        super(
          String.format(
            "Configuration for module %s is already loaded.",
            moduleArchive.getModuleName()
          )
        );
    }
}
