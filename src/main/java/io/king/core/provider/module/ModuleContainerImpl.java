package io.king.core.provider.module;

import io.king.core.api.module.ModuleContainer;
import io.king.core.api.module.ModuleManager;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ModuleContainerImpl implements ModuleContainer {

    private final Map<Class<?>, ModuleManager> modulesContainer = new LinkedHashMap<>();

    public void registerManager(Class<?> clazz, ModuleManager manager) {
        modulesContainer.put(clazz, manager);
    }

    public ModuleManager getModuleManager(Class<?> clazz) {
        return modulesContainer.get(clazz);
    }
}
