package io.king.view.provider.command;

import io.king.core.api.di.Inject;
import io.king.core.provider.cycle.LifeContextImpl;
import io.king.view.provider.Test;
import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;

public final class ModuleView {

    @Inject
    private LifeContextImpl context;

    @Inject
    private Test test;

    @Command(name = "module")
    public void mainModule(Execution execution) {
        execution.sendMessage("Module working");
        test.testInfo("testing info - #DI");
    }
}
