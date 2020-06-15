package io.king.core.provider.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class ModuleProps {
    private final String mainClass, jarFileName;

    public String getPureName() {
        return jarFileName.substring(jarFileName.lastIndexOf("\\") + 1);
    }
}
