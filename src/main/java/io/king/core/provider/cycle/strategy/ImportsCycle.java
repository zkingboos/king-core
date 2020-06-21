package io.king.core.provider.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.module.Module;
import io.king.core.provider.module.ModuleObject;

public final class ImportsCycle implements StrategyCycle {

    private static final ClassLoader LIFE_CYCLE_LOADER = LifeCycle.class.getClassLoader();

    @Override
    public void setup(KingApi kingApi, CycleLoader loader, ModuleObject moduleObject) throws Exception {
        final Module module = moduleObject.getModule();

        for (Class<?> imports : module.imports()) {
            Class.forName(imports.getName(), true, LIFE_CYCLE_LOADER);
        }
    }
}
