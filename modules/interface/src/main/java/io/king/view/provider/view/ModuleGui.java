package io.king.view.provider.view;

import io.king.core.provider.CorePlugin;
import me.saiintbrisson.inventory.inv.GUI;
import me.saiintbrisson.inventory.paginator.PaginatedView;

import java.util.List;

public final class ModuleGui extends PaginatedView<ViewItem> {

    private final static String[] LAYOUT_TEMPLATE = new String[]{
            "OOOOOOOOO",
            "OXXXXXXXO",
            "OXXXXXXXO",
            "OXXXXXXXO",
            "OOOOOOOOO",
            "OOO<O>OOO"
    };

    public ModuleGui(CorePlugin owner, GUI<ViewItem> gui, List<ViewItem> moduleItems) {
        super(owner, "List of Modules", LAYOUT_TEMPLATE, () -> moduleItems);

        setItemProcessor((player, viewItem) -> {
            gui.createNode(player, viewItem).show();
        });
    }
}
