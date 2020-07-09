package io.king.core.provider.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.strategy.StrategyCycle;
import io.king.core.api.module.Module;
import io.king.core.api.module.ModuleConfig;
import io.king.core.provider.module.ModuleObject;

import java.util.NoSuchElementException;

public final class ConfigCycle implements StrategyCycle {

    private final static Class<?> MODULE_CONFIG = ModuleConfig.class;

    @Override
    public void setup(KingApi kingApi, CycleLoader loader, ModuleObject moduleObject) throws Exception {
        final Module module = moduleObject.getModule();

        final Class<?> moduleConfigClass = module.config();
        final Object objectInstance = moduleConfigClass.newInstance();

        if (!MODULE_CONFIG.isInstance(objectInstance)) throw new NoSuchElementException(
                "The config type should be ModuleConfig's type"
        );

        final ModuleConfig moduleConfig = (ModuleConfig) objectInstance;
        moduleObject.setModuleConfigClass(moduleConfigClass);
        moduleObject.setModuleConfig(moduleConfig);
    }
}
