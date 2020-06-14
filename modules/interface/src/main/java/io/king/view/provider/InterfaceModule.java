package io.king.view.provider;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.module.Module;
import io.king.core.api.module.ModulePriority;
import io.king.view.provider.command.ModuleView;
import io.king.view.provider.event.InterfaceEvent;

@Module(
        events = {InterfaceEvent.class},
        services = {Test.class},
        commands = {ModuleView.class},
        priority = ModulePriority.SYSTEM,
        config = InterfaceConfig.class
)
public final class InterfaceModule extends LifeCycle {

    @Override
    public void preInit(LifeContext context) {
        context.getLogger().info("Pre init");
    }

    @Override
    public void init(LifeContext context) {
        context.getLogger().info("Interface module loaded");
    }

    @Override
    public void dispose(LifeContext context) {
        context.getLogger().info("Interface has been unloaded");
    }
}
