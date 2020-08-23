/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.common.io;

import java.io.File;
import java.io.FileFilter;

public final class ModuleFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return pathname.isFile();
    }
}
