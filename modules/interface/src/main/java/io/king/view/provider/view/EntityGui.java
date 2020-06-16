package io.king.view.provider.view;

import io.king.core.api.di.Inject;
import io.king.core.api.di.Injectable;
import io.king.core.api.module.ModuleConfig;
import io.king.core.api.service.Service;
import io.king.core.provider.CorePlugin;
import io.king.core.provider.module.ModuleObject;
import io.king.core.provider.module.ModuleProps;
import io.king.view.provider.time.TimeService;
import lombok.Setter;
import me.saiintbrisson.inventory.ItemBuilder;
import me.saiintbrisson.inventory.inv.GUI;
import me.saiintbrisson.inventory.inv.GUIItem;
import me.saiintbrisson.inventory.inv.GUINode;
import me.saiintbrisson.inventory.paginator.PaginatedView;
import org.bukkit.Material;

@Service
@Injectable
public final class EntityGui extends GUI<ViewItem> {

    @Inject
    private TimeService timeService;

    public EntityGui(@Inject CorePlugin corePlugin) {
        super(corePlugin, "Module Information", 5);
    }

    @Setter
    private PaginatedView<?> paginatedView;

    @Override
    protected void render(GUINode<ViewItem> node, ViewItem object) {
        final ModuleObject moduleObject = object.getModuleObject();
        final ModuleConfig moduleConfig = moduleObject.getModuleConfig();
        final ModuleProps moduleProps = moduleObject.getModuleProps();

        final String hashId = moduleConfig.getHashId();
        final String version = hashId == null ? "No provided version" : hashId;

        final String[] aboutInformation = {"",
                " §eName » §7" + moduleConfig.getName(),
                " §eAuthor » §7" + moduleConfig.getAuthor(),
                " §eVersion » §7" + version,
                " §eDescription » §7" + moduleConfig.getDescription(),
                " §eStage » §7" + moduleObject.getModuleStage(), "",
                " §eLoad time: » §7" + moduleProps.getLoadedAtTime() +
                        " (" + moduleObject.getLoadDuration() + "ms)"
        };

        node.appendItem(new GUIItem<ViewItem>()
                .withItem(new ItemBuilder(Material.PAPER)
                        .name("§cInformation about module")
                        .lore(aboutInformation)
                        .build()
                ).withSlot(1, 1)
                .updateOnClick()
        );

        node.appendItem(new GUIItem<ViewItem>()
                .withItem(new ItemBuilder(Material.BEACON)
                        .name("Back")
                        .build()
                ).withSlot(3, 7)
                .openPaginatedView(paginatedView)
        );
    }
}
