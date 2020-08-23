/*
 * Copyright (c) 2020 codeproton-projects
 */

package io.king.module.common.io;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public final class FileConverter {

    private final static FileFilter MODULE_FILE_FILTER = new ModuleFilter();

    public static URL[] convertFilesFromFolderToUrl(File fileContent) {
        final File[] files = Objects.requireNonNull(fileContent.listFiles(MODULE_FILE_FILTER));
        final URL[] newFilesUrl = new URL[files.length];

        for (int i = 0; i < files.length; i++) {
            newFilesUrl[i] = convertFileToUrl(files[i]);
        }
        return newFilesUrl;
    }

    public static URL convertFileToUrl(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
