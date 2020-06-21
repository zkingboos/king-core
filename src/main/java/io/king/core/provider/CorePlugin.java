package io.king.core.provider;

import io.king.core.api.KingApi;
import io.king.core.api.cycle.CycleLoader;
import io.king.core.api.cycle.LifeContext;
import io.king.core.api.di.InjectionManager;
import io.king.core.api.module.ModuleContainer;
import io.king.core.api.module.ModuleManager;
import io.king.core.api.module.ModuleModel;
import io.king.core.api.service.ServiceManager;
import io.king.core.provider.cycle.CycleLoaderImpl;
import io.king.core.provider.cycle.LifeContextImpl;
import io.king.core.provider.di.InjectionManagerImpl;
import io.king.core.provider.module.ModuleContainerImpl;
import io.king.core.provider.module.ModuleModelImpl;
import io.king.core.provider.service.ServiceManagerImpl;
import io.king.core.provider.yml.FileYml;
import lombok.Getter;
import me.saiintbrisson.commands.CommandFrame;
import me.saiintbrisson.inventory.InventoryFrame;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * @author zkingboos
 */
@Getter
public final class CorePlugin extends JavaPlugin implements KingApi {

    private final ModuleContainer moduleContainer = new ModuleContainerImpl();
    private final ServiceManager serviceManager = new ServiceManagerImpl();
    private final Logger coreLogger = getLogger();
    private final JavaPlugin plugin = this;

    private InjectionManager injectionManager;
    private InventoryFrame inventoryFrame;
    private ModuleManager moduleManager;
    private CommandFrame commandFrame;
    private CycleLoader cycleLoader;
    private LifeContext context;
    private FileYml configYml;

    @Override
    public void onLoad() {
        try {
            context = new LifeContextImpl(serviceManager, null, this);

            injectionManager = new InjectionManagerImpl(serviceManager);
            cycleLoader = new CycleLoaderImpl(injectionManager, serviceManager, this);
            final ModuleModel moduleModel = new ModuleModelImpl(this, cycleLoader);

            coreLogger.info("Trying to load modules from default folder.");
            moduleManager = moduleModel.load();
            coreLogger.info(
                    String.format("Loaded %s modules from default folder.",
                            moduleManager.getModules().size()
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        configYml = new FileYml(this, "config.yml").load();
        commandFrame = new CommandFrame(this);
        inventoryFrame = new InventoryFrame(this);
        inventoryFrame.registerListener();

        /*
         * Register command frame in service
         * Used to share the instance to others modules
         */
        serviceManager.registerServices(
                configYml,
                inventoryFrame,
                commandFrame,
                context,
                this
        );

        try {
            moduleContainer.registerManager(
                    CorePlugin.class,
                    moduleManager.orderByPriority().lifeCycle()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        moduleManager.orderShutdown();
    }
}
