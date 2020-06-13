package io.king.core.provider.yml;

import lombok.experimental.Delegate;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public final class FileYml extends YamlConfiguration {

    private final File ymlFile;

    @Delegate
    private YamlConfiguration configuration;

    public FileYml(Plugin plugin, String file) {
        this.ymlFile = new File(plugin.getDataFolder(), file);
        if (!ymlFile.exists()) plugin.saveResource(file, false);
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
