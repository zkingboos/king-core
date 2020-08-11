/*
 * Copyright codeproton-projects (c) 2020
 */

package core;

import io.king.module.api.di.Inject;
import io.king.module.api.life.Dispose;
import io.king.module.api.life.PostInit;
import io.king.module.api.module.Module;
import io.king.module.api.module.ModuleEntity;

@Module(
  config = TestConfig.class,
  services = {
    TestService.class
  }
)
public final class TestPlugin {

    @PostInit
    public void init(@Inject ModuleEntity entity) {
        entity.getLogger().info("Test module loaded");
    }

    @Dispose
    public void dispose() {
    }
}
