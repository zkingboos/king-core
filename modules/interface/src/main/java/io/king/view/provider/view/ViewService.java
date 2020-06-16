package io.king.view.provider.view;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.di.Inject;
import io.king.core.api.service.Service;
import io.king.core.provider.CorePlugin;
import io.king.core.provider.module.ModuleObject;

import java.util.LinkedList;

@Service
public final class ViewService extends LifeCycle {

    @Inject
    private CorePlugin corePlugin;

    @Inject
    private EntityGui entityGui;

    @Override
    public void init(LifeContext context) {
        final LinkedList<ViewItem> moduleList = new LinkedList<>();
        for (ModuleObject module : corePlugin.getModuleManager().getModules()) {
            moduleList.add(new ViewItem(module));
        }

        final ModuleGui moduleGui = new ModuleGui(corePlugin, entityGui, moduleList);
        entityGui.setPaginatedView(moduleGui);

        context.registerServices(moduleGui);
    }
}
