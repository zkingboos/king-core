package io.king.core.provider.cycle;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.provider.cycle.strategy.CommandCycle;
import io.king.core.provider.cycle.strategy.ConfigCycle;
import io.king.core.provider.cycle.strategy.ImportsCycle;
import io.king.core.provider.cycle.strategy.StrategyCycle;
import io.king.core.provider.module.ModuleObject;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public final class CycleLoaderImpl implements CycleLoader {

    private static final List<StrategyCycle> STRATEGY_CYCLE_LIST = new LinkedList<>();
    private static final Class<?> LIFE_CONTEXT_CLASS = LifeContext.class;
    private static final Class<?> LIFE_CYCLE_CLASS = LifeCycle.class;

    static {
        STRATEGY_CYCLE_LIST.add(new ConfigCycle());
        STRATEGY_CYCLE_LIST.add(new ImportsCycle());
        STRATEGY_CYCLE_LIST.add(new CommandCycle());
    }

    private final LifeContext lifeContext;
    private final KingApi kingApi;

    public void resolveCycle(ModuleObject moduleObject) throws Exception {
        final Class<?> moduleClass = moduleObject.getModuleClass();

        /*
         * Initialize the modules instance
         */
        final Object moduleInstance = initialize(moduleClass);
        moduleObject.setModuleInstance(moduleInstance);

        for (StrategyCycle strategyCycle : STRATEGY_CYCLE_LIST) {
            strategyCycle.setup(kingApi, this, moduleObject);
        }

        notifyModule(moduleInstance);
    }

    //CONTINUE TOMORROW, IM TIRED TODAY
    public Object initialize(Class<?> clazz) throws Exception {
        return clazz.newInstance();
    }

    public void notifyModule(Object moduleInstance) {
        if (LIFE_CYCLE_CLASS.isInstance(moduleInstance)) {
            final LifeCycle context = (LifeCycle) moduleInstance;
            context.init(lifeContext);
        }
    }
}
