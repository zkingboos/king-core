package io.king.core.api.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.provider.module.ModuleObject;

/**
 * Strategy Pattern
 */
public interface StrategyCycle {

    /**
     * Make unattached tasks
     *
     * @param kingApi      instance of king api
     * @param loader       instance of loader
     * @param moduleObject instance of module object
     * @throws Exception something can break
     */
    void setup(KingApi kingApi, CycleLoader loader, ModuleObject moduleObject) throws Exception;
}
