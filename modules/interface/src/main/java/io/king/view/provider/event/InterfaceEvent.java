package io.king.view.provider.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class InterfaceEvent implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Welcome to server, we're using king-core system!");
    }
}
