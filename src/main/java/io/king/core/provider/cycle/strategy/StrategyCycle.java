package io.king.core.provider.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.provider.module.ModuleObject;

public interface StrategyCycle {

    void setup(KingApi kingApi, CycleLoader loader, ModuleObject moduleObject) throws Exception;
}
