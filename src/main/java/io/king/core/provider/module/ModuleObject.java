package io.king.core.provider.module;

import io.king.core.api.module.Module;
import io.king.core.api.module.ModuleConfig;
import io.king.core.api.module.stage.ModuleStage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.File;

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
}
