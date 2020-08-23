/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.provider.module;

import io.king.module.api.module.Configuration;
import io.king.module.api.module.Module;
import lombok.Data;

import java.io.File;

@Data
public final class ModuleArchive {

    private String moduleName;

    private File moduleFile;

    private Class<?> moduleMainClass;

    private Module moduleAnnotation;

    private Configuration moduleConfiguration;
}
