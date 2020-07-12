package io.king.core.provider.cycle.strategy;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.cycle.LifeCycle;
import io.king.core.api.cycle.strategy.StrategyCycle;
import io.king.core.api.module.Module;
import io.king.core.api.service.Service;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.module.ModuleObject;

import java.util.NoSuchElementException;

public final class ServiceCycle implements StrategyCycle {

    private final static Class<Service> SERVICE_CLASS = Service.class;

    @Override
    public void setup(
      KingApi kingApi,
      CycleLoader loader,
      ModuleObject moduleObject
    ) throws Exception {
        final ServiceManager serviceManager = loader.getServiceManager();
        final Module module = moduleObject.getModule();

        final LifeContext lifeContext = loader.resolveContext(moduleObject);

        for (Class<?> service : module.services()) {
            resolveServiceContext(
              moduleObject,
              service,
              loader,
              lifeContext,
              serviceManager
            );
        }
    }

    public void resolveServiceContext(
      ModuleObject moduleObject,
      Class<?> service,
      CycleLoader loader,
      LifeContext lifeContext,
      ServiceManager serviceManager
    ) throws Exception {
        final Service serviceAnnotation = service.getAnnotation(SERVICE_CLASS);
        if (serviceAnnotation == null)
            throw new NoSuchElementException("Service should be annotated with @Service");

        for (Class<?> subService : serviceAnnotation.value()) {
            if(service == subService) {
                throw new StackOverflowError(
                  "Service and subservice are the same."
                );
            }

            resolveServiceContext(
              moduleObject,
              subService,
              loader,
              lifeContext,
              serviceManager
            );
        }

        final Object objectInstance = loader.initialize(service);
        final LifeCycle lifeCycle = loader.initializeLife(objectInstance);

        serviceManager.registerService(objectInstance);
        loader.notifyModule(lifeCycle, lifeContext);

        moduleObject.addToBuffer(service);
    }
}