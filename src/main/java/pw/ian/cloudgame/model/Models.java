/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.model;

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
public abstract class Models<T extends Model> {

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

    /**
     * Returns the model if the add was successful
     *
     * @param id
     * @param model
     * @return
     */
    protected void add(T model) {
        map.put(model.getId(), model);
    }

    public boolean has(String id) {
        return findById(id) != null;
    }

    public T findById(String id) {
        return map.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    /**
     * Returns true if the remove was successful
     *
     * @param id
     * @return
     */
    public boolean remove(String id) {
        map.remove(id);
        return true;
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
