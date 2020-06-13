package io.king.core.provider.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.module.Module;
import io.king.core.api.module.ModuleConfig;
import io.king.core.provider.module.ModuleObject;

public final class ConfigCycle implements StrategyCycle {

    @Override
    public void setup(KingApi kingApi, CycleLoader loader, ModuleObject moduleObject) throws Exception {
        final Module module = moduleObject.getModule();

        final Class<?> moduleConfigClass = module.config();
        final ModuleConfig moduleConfig = (ModuleConfig) moduleConfigClass.newInstance();

        moduleObject.setModuleConfigClass(moduleConfigClass);
        moduleObject.setModuleConfig(moduleConfig);
    }
}
