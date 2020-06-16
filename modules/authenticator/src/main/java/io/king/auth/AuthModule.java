package io.king.auth;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.di.Inject;
import io.king.core.api.module.Module;
import io.king.view.provider.InterfaceModule;
import io.king.view.provider.time.TimeService;

@Module(
        services = {TimeService.class},
        softDepend = {InterfaceModule.class},
        config = AuthConfig.class
)
public final class AuthModule extends LifeCycle {

    @Inject
    private TimeService timeService;

    @Override
    public void init(LifeContext context) {
        context.getLogger().info("Auth module initialized");
    }
}
