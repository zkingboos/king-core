package io.king.core.provider.cycle;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.di.Inject;
import io.king.core.api.di.Injectable;
import io.king.core.api.di.InjectionManager;
import io.king.core.api.module.stage.ModuleStage;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.cycle.strategy.*;
import io.king.core.provider.module.ModuleObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public final class CycleLoaderImpl implements CycleLoader {

    private static final List<StrategyCycle> STRATEGY_CYCLE_LIST = new LinkedList<>();
    private static final Class<Injectable> INJECTABLE_CLASS = Injectable.class;
    private static final Class<?> LIFE_CYCLE_CLASS = LifeCycle.class;
    private static final Class<Inject> INJECT_CLASS = Inject.class;

    static {
        STRATEGY_CYCLE_LIST.add(new ConfigCycle());
        STRATEGY_CYCLE_LIST.add(new ImportsCycle()); //template
        STRATEGY_CYCLE_LIST.add(new ServiceCycle());
        STRATEGY_CYCLE_LIST.add(new CommandCycle());
        STRATEGY_CYCLE_LIST.add(new EventCycle());
    }

    private final Map<Class<?>, LifeContext> lifeContextMap = new LinkedHashMap<>();

    private final InjectionManager injectionManager;
    private final ServiceManager serviceManager;
    private final KingApi kingApi;

    public void resolveCycle(ModuleObject moduleObject) throws Exception {
        if (moduleObject.getModuleStage() == ModuleStage.LOADED) return;
        final Class<?> moduleClass = moduleObject.getModuleClass();

        /*
         * Initialize the modules instance
         */
        final Object moduleInstance = initialize(moduleClass);
        moduleObject.setModuleInstance(moduleInstance);

        final LifeCycle lifeCycle = initializeLife(moduleInstance);
        final LifeContext lifeContext = resolveContext(moduleObject);

        preNotifyModule(lifeCycle, lifeContext);

        for (StrategyCycle strategyCycle : STRATEGY_CYCLE_LIST) {
            strategyCycle.setup(kingApi, this, moduleObject);
        }

        serviceManager.registerService(moduleInstance);
        moduleObject.setModuleStage(ModuleStage.LOADED);
        notifyModule(lifeCycle, lifeContext);
    }

    @Override
    public LifeContext resolveContext(ModuleObject moduleObject) {
        final Class<?> moduleClass = moduleObject.getModuleClass();

        final LifeContext lifeContext = lifeContextMap.get(moduleClass);
        if (lifeContext != null) return lifeContext;

        final LifeContextImpl value = new LifeContextImpl(
                serviceManager,
                moduleObject,
                (JavaPlugin) kingApi
        );

        lifeContextMap.put(moduleClass, value);
        return value;
    }

    @Override
    public Object initialize(Class<?> clazz) throws Exception {
        final boolean isPresent = clazz.isAnnotationPresent(INJECTABLE_CLASS);
        final Object objectInstance = isPresent ?
                injectionManager.injectIntoClass(clazz) :
                clazz.newInstance();

        injectionManager.injectIntoService(objectInstance, clazz);
        return objectInstance;
    }

    @Override
    public void preNotifyModule(LifeCycle lifeCycle, LifeContext lifeContext) {
        if (lifeCycle == null) return;
        lifeCycle.preInit(lifeContext);
    }

    @Override
    public void notifyModule(LifeCycle lifeCycle, LifeContext lifeContext) {
        if (lifeCycle == null) return;
        lifeCycle.init(lifeContext);
    }

    @Override
    public void notifyDisposeModule(ModuleObject objectModule, LifeContext lifeContext) {
        final Object moduleInstance = objectModule.getModuleInstance();
        final LifeCycle lifeCycle = initializeLife(moduleInstance);

        if (lifeCycle == null) return;
        lifeCycle.dispose(lifeContext);
    }

    @Override
    public LifeCycle initializeLife(Object moduleInstance) {
        if (!LIFE_CYCLE_CLASS.isInstance(moduleInstance)) return null;
        return (LifeCycle) moduleInstance;
    }
}
