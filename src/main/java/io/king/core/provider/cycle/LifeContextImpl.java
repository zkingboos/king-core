package io.king.core.provider.cycle;

import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeEvent;
import io.king.core.api.module.ModuleConfig;
import io.king.core.api.service.ServiceEntity;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.module.ModuleObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.function.Consumer;
import java.util.logging.Logger;

@Getter
@RequiredArgsConstructor
public final class LifeContextImpl implements LifeContext {

    private final ServiceManager serviceManager;
    private final ModuleObject moduleObject;
    private final JavaPlugin corePlugin;
    private final File moduleDirectory;
    private final LifeEvent lifeEvent;

    @Override
    public <T> T getService(Class<?> registration) {
        final ServiceEntity<?> service = serviceManager.getRegistrationService(registration);
        if (service == null) return null;

        return (T) service.getService();
    }

    @Override
    public File getDataFolder() throws AssertionError {
        if(!isModule()) throw new AssertionError("Isn't an module");

        final ModuleConfig moduleConfig = moduleObject.getModuleConfig();
        final String path = String.format("/%s/", moduleConfig.getName());

        return new File(moduleDirectory, path);
    }

    @Override
    public void registerServices(Object... services) {
        serviceManager.registerServices(services);
    }

    @Override
    public boolean isModule() {
        return moduleObject != null;
    }

    @Override
    public <T> void registerEvent(Class<T> clazz, Consumer<T> consumer) {
        lifeEvent.registerEvent(clazz, consumer);
    }

    @Override
    public Logger getLogger() {
        return corePlugin.getLogger();
    }
}
