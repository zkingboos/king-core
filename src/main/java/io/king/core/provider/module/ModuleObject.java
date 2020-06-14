package io.king.core.provider.module;

import io.king.core.api.module.Module;
import io.king.core.api.module.ModuleConfig;
import io.king.core.api.module.stage.ModuleStage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public final class ModuleObject {

    private final String mainClass;
    private final Class<?> moduleClass;
    private final Module module;

    @Setter
    private ModuleStage moduleStage = ModuleStage.NOT_LOADED;

    @Setter
    private Object moduleInstance;

    @Setter
    private Class<?> moduleConfigClass;

    @Setter
    private ModuleConfig moduleConfig;
}
