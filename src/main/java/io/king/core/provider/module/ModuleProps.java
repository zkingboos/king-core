package io.king.core.provider.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public final class ModuleProps {

    private final Class<?> mainClass;
    private final String jarFileName;
    private final long loadedAtTime;

    public String getPureName() {
        return jarFileName.substring(jarFileName.lastIndexOf("\\") + 1);
    }
}
