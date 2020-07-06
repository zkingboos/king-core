package io.king.core.provider.module;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.king.core.api.module.Module;
import io.king.core.api.module.ModuleConfig;
import io.king.core.api.module.stage.ModuleStage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Getter
@RequiredArgsConstructor
public final class ModuleObject {

    private final ModuleProps moduleProps;
    private final Class<?> moduleClass;
    private final long loadDuration;
    private final Module module;
    private final File fatJar;

    @Setter
    private ModuleStage moduleStage = ModuleStage.NOT_LOADED;

    @Setter
    private Object moduleInstance;

    @Setter
    private Class<?> moduleConfigClass;

    @Setter
    private ModuleConfig moduleConfig;

    public void saveResource(File folder, String fileName) throws IOException {
        final JarFile jarFile = new JarFile(fatJar);
        final JarEntry jarEntry = jarFile.getJarEntry(fileName);

        final InputStream inputStream = jarFile.getInputStream(jarEntry);

        final byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        final FileOutputStream outputStream = new FileOutputStream(folder);
        outputStream.write(buffer);

        outputStream.close();
        inputStream.close();
        jarFile.close();
    }
}
