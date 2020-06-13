package io.king.core.provider;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.module.ModuleContainer;
import io.king.core.api.module.ModuleManager;
import io.king.core.api.module.ModuleModel;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.cycle.CycleLoaderImpl;
import io.king.core.provider.cycle.LifeContextImpl;
import io.king.core.provider.module.ModuleContainerImpl;
import io.king.core.provider.module.ModuleModelImpl;
import io.king.core.provider.service.ServiceManagerImpl;
import io.king.core.provider.yml.FileYml;
import lombok.Getter;
import me.saiintbrisson.commands.CommandFrame;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@Getter
public final class CorePlugin extends JavaPlugin implements KingApi {

    private final ModuleContainer moduleContainer = new ModuleContainerImpl();
    private final ServiceManager serviceManager = new ServiceManagerImpl();
    private final Logger coreLogger = getLogger();

    private ModuleManager moduleManager;
    private CommandFrame commandFrame;
    private CycleLoader cycleLoader;
    private LifeContext context;
    private FileYml configYml;

    @Override
    public void onLoad() {
        try {
            context = new LifeContextImpl(this, serviceManager);
            cycleLoader = new CycleLoaderImpl(context, this);
            final ModuleModel moduleModel = new ModuleModelImpl(this, cycleLoader, context);

            coreLogger.info("Trying to load modules from default folder.");
            moduleManager = moduleModel.load();
            coreLogger.info(
                    String.format("Loaded %s modules from default folder.",
                            moduleManager.getModules().size())
            );
        } catch (Exception e) {
            e.printStackTrace();
            coreLogger.warning(e.getMessage());
        }
    }

    @Override
    public void onEnable() {
        configYml = new FileYml(this, "config.yml").load();
        commandFrame = new CommandFrame(this);

        /*
         * Register command frame in service
         * Used to share the instance to others modules
         */
        serviceManager.registerService(commandFrame);

        try {
            moduleContainer.registerManager(
                    CorePlugin.class,
                    moduleManager.lifeCycle()
            );
        } catch (Exception e) {
            e.printStackTrace();
            coreLogger.warning(e.getMessage());
        }
    }
}
