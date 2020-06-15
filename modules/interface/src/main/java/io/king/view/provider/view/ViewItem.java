package io.king.view.provider.view;

import io.king.core.api.module.ModuleConfig;
import io.king.core.provider.module.ModuleObject;
import io.king.core.provider.module.ModuleProps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.inventory.ItemBuilder;
import me.saiintbrisson.inventory.paginator.PaginatedItem;
import me.saiintbrisson.inventory.paginator.PaginatedViewHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@RequiredArgsConstructor
public final class ViewItem implements PaginatedItem {

    private final ModuleObject moduleObject;

    @Override
    public ItemStack toItemStack(Player player, PaginatedViewHolder viewHolder) {
        final ModuleConfig moduleConfig = moduleObject.getModuleConfig();
        final ModuleProps moduleProps = moduleObject.getModuleProps();

        return new ItemBuilder(
                Material.COMMAND
        ).name(
                moduleConfig.getName()
        ).lore(" §cArquivo ", " §c" + moduleProps.getPureName()).build();
    }
}
