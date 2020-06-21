package io.king.core.provider.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.module.Module;
import io.king.core.provider.module.ModuleObject;
import me.saiintbrisson.commands.CommandFrame;

public final class CommandCycle implements StrategyCycle {

    @Override
    public void setup(KingApi kingApi, CycleLoader loader, ModuleObject moduleObject) throws Exception {
        final Module module = moduleObject.getModule();
        final CommandFrame commandFrame = kingApi.getCommandFrame();

        final LifeContext lifeContext = loader.resolveContext(moduleObject);

        for (Class<?> command : module.commands()) {
            final Object instance = loader.initialize(command);
            final LifeCycle lifeCycle = loader.initializeLife(instance);

            loader.notifyModule(lifeCycle, lifeContext);
            commandFrame.register(instance);
        }
    }
}
