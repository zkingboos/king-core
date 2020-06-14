package io.king.auth;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.service.Service;

@Service
public final class AuthService extends LifeCycle {

    @Override
    public void init(LifeContext context) {
        //test.testInfo("king boos");
    }
}
