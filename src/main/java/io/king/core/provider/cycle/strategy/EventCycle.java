package io.king.core.provider.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.module.Module;
import io.king.core.provider.module.ModuleObject;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.NoSuchElementException;

public final class EventCycle implements StrategyCycle {

    private final static Class<?> BUKKIT_LISTENER_CLASS = Listener.class;

    @Override
    public void setup(KingApi kingApi, CycleLoader loader, ModuleObject moduleObject) throws Exception {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        final Module module = moduleObject.getModule();

        final LifeContext lifeContext = loader.resolveContext(moduleObject);

        for (Class<? extends Listener> event : module.events()) {
            final Object objectInstance = loader.initialize(event);
            if (!BUKKIT_LISTENER_CLASS.isInstance(objectInstance)) throw new NoSuchElementException(
                    "Event type should be instance of Listener"
            );

            final LifeCycle lifeCycle = loader.initializeLife(objectInstance);

            pluginManager.registerEvents(
                    (Listener) objectInstance, kingApi.getPlugin()
            );
            loader.notifyModule(lifeCycle, lifeContext);
        }
    }
}
