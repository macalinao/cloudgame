/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author ian
 * @param <T>
 */
public abstract class Models<T> {

    private final ModelManager modelManager;

    private final File file;

    private Map<String, T> map;

    public Models(ModelManager modelManager, String type) {
        this.modelManager = modelManager;
        this.file = new File(modelManager.getModelsFolder(), type + ".yml");
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public File getFile() {
        return file;
    }

    public boolean add(String id, T model) {
        return map.put(id, model) == null;
    }

    public T findById(String id) {
        return map.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<T>(map.values());
    }

    public final void load() throws IOException {
        map = new HashMap<>();
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
}
