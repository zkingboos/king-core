package io.king.core.api.cycle;

import io.king.core.provider.module.ModuleObject;

/**
 * This class is a responsibility to load @Module annotations
 * The all life-cycles are be loaded here
 */
public interface CycleLoader {

    /**
     * Used to resolve life-cycles from @ServiceManager
     * @param moduleObject is module file converted to POO
     * @throws Exception anything can break here
     */
    void resolveCycle(ModuleObject moduleObject) throws Exception;
}
