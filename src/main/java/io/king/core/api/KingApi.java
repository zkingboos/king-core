package io.king.core.api;

import io.king.core.api.module.ModuleManager;
import me.saiintbrisson.minecraft.command.CommandFrame;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Heart of core
 */
public interface KingApi {

    /**
     * https://github.com/SaiintBrisson/command-framework
     *
     * @return instance to register others plugins
     */
    CommandFrame getCommandFrame();

    /**
     * @return java plugin instance
     */
    JavaPlugin getPlugin();

    /**
     * @return instance of module manager
     */
    ModuleManager getModuleManager();
}