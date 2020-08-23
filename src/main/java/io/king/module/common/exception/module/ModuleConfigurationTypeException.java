/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.common.exception.module;

import io.king.module.provider.module.ModuleArchive;

public final class ModuleConfigurationTypeException extends ClassCastException {

    public ModuleConfigurationTypeException(ModuleArchive moduleArchive) {
        super(
          String.format(
            "Type of configuration class of module %s is not valid.",
            moduleArchive.getModuleName()
          )
        );
    }
}
