package io.king.core.provider.cycle;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.cycle.LifeEvent;
import io.king.core.api.cycle.strategy.StrategyCycle;
import io.king.core.api.di.Inject;
import io.king.core.api.di.Injectable;
import io.king.core.api.di.InjectionManager;
import io.king.core.api.module.Module;
import io.king.core.api.module.stage.ModuleStage;
import io.king.core.api.service.ServiceEntity;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.cycle.event.ModuleInitialized;
import io.king.core.provider.cycle.strategy.*;
import io.king.core.provider.module.ModuleObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

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
    private final LifeEvent lifeEvent;
    private final KingApi kingApi;

    @Setter
    private File moduleDirectory;

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
        serviceManager.registerService(moduleInstance);

        for (StrategyCycle strategyCycle : STRATEGY_CYCLE_LIST) {
            strategyCycle.setup(kingApi, this, moduleObject);
        }

        moduleObject.setModuleStage(ModuleStage.LOADED);
        lifeEvent.notifyListeners(new ModuleInitialized(moduleObject));

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
          (JavaPlugin) kingApi,
          moduleDirectory,
          lifeEvent
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

        try (LifeCycle lifeCycle = initializeLife(moduleInstance)) {
            if (lifeCycle != null)
                lifeCycle.dispose(lifeContext);
        } catch (Exception $) {
            $.printStackTrace();
        }
    }

    @Override
    public void removalServices(ModuleObject moduleObject, LifeContext lifeContext) {
        for (Class<?> service : moduleObject.getBufferServices()) {
            final ServiceEntity<?> registration = serviceManager.getRegistrationService(
              service
            );

            if(registration == null) continue;
            final Object currentService = registration.getService();

            try (LifeCycle cycle = initializeLife(currentService)) {
                if(cycle != null)
                    cycle.dispose(lifeContext);
            } catch (Exception $) {
                $.printStackTrace();
            }
        }
    }

    @Override
    public LifeCycle initializeLife(Object moduleInstance) {
        if (!LIFE_CYCLE_CLASS.isInstance(moduleInstance)) return null;
        return (LifeCycle) moduleInstance;
    }
}
