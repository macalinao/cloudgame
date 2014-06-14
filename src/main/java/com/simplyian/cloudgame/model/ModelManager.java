/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.model.arena.Arenas;
import com.simplyian.cloudgame.model.region.Regions;

/**
 *
 * @author ian
 */
public class ModelManager {

    private final CloudGame plugin;

    private final File modelsFolder;

    private final Regions regions;

    private final Arenas arenas;

    public ModelManager(CloudGame plugin) {
        this.plugin = plugin;
        this.modelsFolder = new File(plugin.getDataFolder(), "models/");

        this.regions = new Regions(this);
        this.arenas = new Arenas(this);
    }

    public void load() {
        modelsFolder.mkdirs();
        try {
            regions.load();
            arenas.load();
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Error loading models!", ex);
        }
    }

    public void save() {
        modelsFolder.mkdirs();
        try {
            regions.save();
            arenas.save();
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Error saving models!", ex);
        }
    }

    public CloudGame getPlugin() {
        return plugin;
    }

    public File getModelsFolder() {
        return modelsFolder;
    }

    public Regions getRegions() {
        return regions;
    }

    public Arenas getArenas() {
        return arenas;
    }
}
