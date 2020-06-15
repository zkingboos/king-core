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

    public void tryLoadLife(ModuleObject module) throws Exception {
        final Class<?> moduleClass = module.getModuleClass();
        for (Class<?> soft : module.getModule().softDepend()) {
            if (moduleClass == soft) throw new StackOverflowError(
                    "Overflow on softDepend at module " + moduleClass.getSimpleName()
            );

            final ModuleObject moduleType = findModuleByType(soft);
            if (moduleType == null) throw new StackOverflowError(
                    "Module not found"
            );

            tryLoadLife(moduleType);
        }

        cycleLoader.resolveCycle(module);
    }

    @Override
    public ModuleManager lifeCycle() throws Exception {
        for (ModuleObject module : modules) {
            tryLoadLife(module);
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
