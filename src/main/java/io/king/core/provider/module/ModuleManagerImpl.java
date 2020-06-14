package io.king.core.provider.module;

import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.module.ModuleManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Comparator;
import java.util.List;

@Getter
@RequiredArgsConstructor
public final class ModuleManagerImpl implements ModuleManager {

    private final List<ModuleObject> modules;
    private final CycleLoader cycleLoader;
    private final JavaPlugin plugin;

    @Override
    public ModuleObject findModuleByType(Class<?> clazz) {
        for (ModuleObject module : modules) {
            if (module.getModuleClass() == clazz) return module;
        }

        return null;
    }

    @Override
    public ModuleManager lifeCycle() throws Exception {
        for (ModuleObject module : modules) {
            final Class<?> moduleClass = module.getModuleClass();
            for (Class<?> soft : module.getModule().softDepend()) {
                if (moduleClass == soft) throw new StackOverflowError(
                        "Overflow on softDepend at module " + moduleClass.getSimpleName()
                );

                final ModuleObject moduleType = findModuleByType(soft);
                cycleLoader.resolveCycle(moduleType);
            }
            cycleLoader.resolveCycle(module);
        }
        return this;
    }

    @Override
    public ModuleManager orderByPriority() {
        modules.sort(Comparator.comparingInt(md -> md.getModule().priority().ordinal()));
        return this;
    }

    @Override
    public void orderShutdown() {
        for (ModuleObject module : modules) {
            cycleLoader.notifyDisposeModule(module);
        }
    }
}
