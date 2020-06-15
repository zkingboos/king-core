package io.king.view.provider.command;

import io.king.core.api.di.Inject;
import io.king.view.provider.view.ModuleGui;
import me.saiintbrisson.commands.Execution;
import me.saiintbrisson.commands.annotations.Command;
import org.bukkit.entity.Player;

public final class ModuleView {

    @Inject
    private ModuleGui moduleGui;

    @Command(name = "module")
    public void mainModule(Execution execution) {
        final Player player = execution.getPlayer();
        moduleGui.show(player);
    }
}
