package io.king.core.provider.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class ModuleProps {

    private final Class<?> mainClass;
    private final String jarFileName;

    public String getPureName() {
        return jarFileName.substring(jarFileName.lastIndexOf("\\") + 1);
    }
}
