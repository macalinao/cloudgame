/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author ian
 */
public abstract class Models<T> {

    private final ModelManager modelManager;

    private final File file;

    public Models(ModelManager modelManager, String type) {
        this.modelManager = modelManager;
        this.file = new File(modelManager.getModelsFolder(), type + ".yml");
    }

    public final void load() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        YamlConfiguration modelsConf = YamlConfiguration.loadConfiguration(file);
        load(modelsConf);
    }

    public final void save() throws IOException {
        YamlConfiguration modelsConf = new YamlConfiguration();
        save(modelsConf);
        modelsConf.save(file);
    }

    protected abstract void load(YamlConfiguration modelsConf);

    protected abstract void save(YamlConfiguration modelsConf);

    public abstract List<T> findAll();
}
