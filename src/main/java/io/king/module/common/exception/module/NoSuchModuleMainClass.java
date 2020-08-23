/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.common.exception.module;

import java.util.NoSuchElementException;

public final class NoSuchModuleMainClass extends NoSuchElementException {

    public NoSuchModuleMainClass(String moduleFileName) {
        super(
          String.format(
            "No module main class found to file %s",
            moduleFileName
          )
        );
    }
}
