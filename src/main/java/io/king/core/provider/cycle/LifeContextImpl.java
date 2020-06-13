package io.king.core.provider.cycle;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.service.ServiceEntity;
import io.king.core.api.service.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@RequiredArgsConstructor
public final class LifeContextImpl implements LifeContext {

    private final JavaPlugin plugin;
    private final ServiceManager serviceManager;

    @Override
    public <T> T getService(Class<?> registration) {
        final ServiceEntity<?> service = serviceManager.getRegistrationService(registration);
        if (service == null) return null;

        return (T) service.getService();
    }

    @Override
    public Logger getLogger() {
        return plugin.getLogger();
    }

    @Override
    public JavaPlugin getCorePlugin() {
        return plugin;
    }
}
