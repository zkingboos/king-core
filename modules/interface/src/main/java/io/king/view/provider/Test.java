package io.king.view.provider;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.di.Inject;
import io.king.core.api.service.Service;

@Service
public final class Test extends LifeCycle {

    @Inject
    private LifeContext lifeContext;

    @Override
    public void init(LifeContext context) {
        lifeContext.getLogger().info("TestService has been initialized");
    }

    public void testInfo(String info) {
        System.out.println(info);
    }
}
