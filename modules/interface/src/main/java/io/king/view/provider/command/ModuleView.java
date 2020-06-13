package io.king.view.provider.command;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.service.Inject;
import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;

public final class ModuleView {

    @Inject
    private LifeContext context;

    @Command(name = "module")
    public void mainModule(Execution execution) {
        //TODO: Things should be added here
    }
}
