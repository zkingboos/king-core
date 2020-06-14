package io.king.auth;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.module.Module;
import io.king.core.api.module.ModulePriority;
import io.king.view.provider.InterfaceModule;

@Module(
        softDepend = {InterfaceModule.class},
        services = {AuthService.class},
        priority = ModulePriority.SYSTEM,
        config = AuthConfig.class
)
public final class AuthModule extends LifeCycle {

    @Override
    public void init(LifeContext context) {
        context.getLogger().info("Auth module initialized");
    }
}
