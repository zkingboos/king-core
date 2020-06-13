package io.king.core.provider.cycle;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.service.Inject;
import io.king.core.api.service.ServiceEntity;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.cycle.strategy.CommandCycle;
import io.king.core.provider.cycle.strategy.ConfigCycle;
import io.king.core.provider.cycle.strategy.ImportsCycle;
import io.king.core.provider.cycle.strategy.StrategyCycle;
import io.king.core.provider.module.ModuleObject;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public final class CycleLoaderImpl implements CycleLoader {

    private static final List<StrategyCycle> STRATEGY_CYCLE_LIST = new LinkedList<>();
    private static final Class<?> LIFE_CYCLE_CLASS = LifeCycle.class;
    private static final Class<Inject> INJECT_CLASS = Inject.class;

    static {
        STRATEGY_CYCLE_LIST.add(new ConfigCycle());
        STRATEGY_CYCLE_LIST.add(new ImportsCycle());
        STRATEGY_CYCLE_LIST.add(new CommandCycle());
    }

    private final ServiceManager serviceManager;
    private final LifeContext lifeContext;
    private final KingApi kingApi;

    public void resolveCycle(ModuleObject moduleObject) throws Exception {
        final Class<?> moduleClass = moduleObject.getModuleClass();

        /*
         * Initialize the modules instance
         */
        final Object moduleInstance = initialize(moduleClass);
        moduleObject.setModuleInstance(moduleInstance);

        final LifeCycle lifeCycle = initializeLife(moduleInstance);
        preNotifyModule(lifeCycle);

        for (StrategyCycle strategyCycle : STRATEGY_CYCLE_LIST) {
            strategyCycle.setup(kingApi, this, moduleObject);
        }

        notifyModule(lifeCycle);
    }

    public Object initialize(Class<?> clazz) throws Exception {
        final Object objectInstance = clazz.newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            final boolean isPresent = field.isAnnotationPresent(INJECT_CLASS);
            if(!isPresent) continue;

            final Class<?> type = field.getType();
            final ServiceEntity<?> service = serviceManager.getRegistrationService(type);
            if(service == null) throw new NoSuchElementException();

            final Object serviceObject = service.getService();
            final boolean accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(objectInstance, serviceObject);
            field.setAccessible(accessible);
        }

        return objectInstance;
    }

    @Override
    public void preNotifyModule(LifeCycle lifeCycle){
        if(lifeCycle == null) return;
        lifeCycle.preInit(lifeContext);
    }

    @Override
    public void notifyModule(LifeCycle lifeCycle) {
        if(lifeCycle == null) return;
        lifeCycle.init(lifeContext);
    }

    @Override
    public LifeCycle initializeLife(Object moduleInstance) {
        if (!LIFE_CYCLE_CLASS.isInstance(moduleInstance)) return null;
        return (LifeCycle) moduleInstance;
    }
}
