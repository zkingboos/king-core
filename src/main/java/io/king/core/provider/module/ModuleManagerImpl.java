package io.king.core.provider.module;

import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.module.ModuleManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Comparator;
import java.util.List;

@Getter
@RequiredArgsConstructor
public final class ModuleManagerImpl implements ModuleManager {

    private final JavaPlugin plugin;
    private final List<ModuleObject> modules;
    private final CycleLoader cycleLoader;

    public ModuleManager lifeCycle() throws Exception {
        orderByPriority();
        for (ModuleObject module : modules) {
            cycleLoader.resolveCycle(module);
        }
        return this;
    }

    public void orderByPriority() {
        modules.sort(Comparator.comparingInt(md -> md.getModule().priority().ordinal()));
    }
}
