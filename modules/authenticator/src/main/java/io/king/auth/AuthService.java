package io.king.auth;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.di.Inject;
import io.king.core.api.service.Service;
import io.king.view.provider.Test;

@Service
public final class AuthService extends LifeCycle {

    @Inject
    private Test test;

    @Override
    public void init(LifeContext context) {
        test.testInfo("king");
    }
}
