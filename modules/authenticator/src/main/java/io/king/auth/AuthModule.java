package io.king.auth;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.module.Module;

@Module(
        config = AuthConfig.class
)
public final class AuthModule extends LifeCycle {

    @Override
    public void init(LifeContext context) {
        context.getLogger().info("Auth module initialized");
    }
}
