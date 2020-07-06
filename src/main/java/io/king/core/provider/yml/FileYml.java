package io.king.core.provider.yml;

import io.king.core.provider.module.ModuleObject;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class FileYml {

    private final File ymlFile;
    private final String file;

    @Getter
    private YamlConfiguration configuration;

    public FileYml(File folder, String file) {
        this.ymlFile = new File(folder, file);
        this.file = file;

        if (!folder.exists()) folder.mkdir();
    }

    /**
     * Load from java plugin context
     *
     * @param plugin instance of plugin
     * @return instance of file yml
     */
    public FileYml loadFromPlugin(JavaPlugin plugin) {
        if (!ymlFile.exists()) plugin.saveResource(file, false);
        return load();
    }

    /**
     * Load from king-core module context
     *
     * @param object instance of module object
     * @return instance of file yml
     */
    public FileYml loadFromModule(ModuleObject object) throws IOException {
        if (!ymlFile.exists()) object.saveResource(ymlFile, file);
        return load();
    }

    public FileYml saveAndReload() throws IOException {
        configuration.save(ymlFile);
        return load();
    }

    public FileYml load() {
        this.configuration = YamlConfiguration.loadConfiguration(ymlFile);
        return this;
    }
}
