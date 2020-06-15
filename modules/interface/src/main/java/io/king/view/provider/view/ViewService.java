package io.king.view.provider.view;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.di.Inject;
import io.king.core.api.service.Service;
import io.king.core.provider.CorePlugin;

@Service
public final class ViewService extends LifeCycle {

    @Inject
    private CorePlugin corePlugin;

    @Override
    public void init(LifeContext context) {
        final ModuleGui moduleGui = new ModuleGui(corePlugin);
        context.registerServices(moduleGui);
    }
}
