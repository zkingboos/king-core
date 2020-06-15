package io.king.view.provider.view;

import io.king.core.api.module.ModuleManager;
import io.king.core.provider.CorePlugin;
import io.king.core.provider.module.ModuleObject;
import lombok.NonNull;
import me.saiintbrisson.inventory.paginator.PaginatedView;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public final class ModuleGui {

    private final static String[] LAYOUT_TEMPLATE = new String[]{
            "OOOOOOOOO",
            "OXXXXXXXO",
            "OXXXXXXXO",
            "OXXXXXXXO",
            "OOOOOOOOO",
            "OOO<O>OOO"
    };

    private final List<ViewItem> moduleItems;
    private final PaginatedView<ViewItem> paginatedView;

    public ModuleGui(@NonNull CorePlugin owner) {
        moduleItems = new LinkedList<>();

        final ModuleManager moduleManager = owner.getModuleManager();
        for (ModuleObject module : moduleManager.getModules()) {
            moduleItems.add(new ViewItem(module));
        }

        paginatedView = new PaginatedView<>(
                owner,
                "List of Modules",
                LAYOUT_TEMPLATE,
                () -> moduleItems
        );
    }

    public void show(Player player) {
        paginatedView.showInventory(player);
    }
}
