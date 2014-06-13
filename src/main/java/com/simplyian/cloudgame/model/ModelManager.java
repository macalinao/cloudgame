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
import com.simplyian.cloudgame.model.room.Rooms;

/**
 *
 * @author ian
 */
public class ModelManager {

    private final CloudGame plugin;

    private final File modelsFolder;

    private final Rooms rooms;

    private final Arenas arenas;

    public ModelManager(CloudGame plugin) {
        this.plugin = plugin;
        this.modelsFolder = new File(plugin.getDataFolder(), "models/");

        this.rooms = new Rooms(this);
        this.arenas = new Arenas(this);
    }

    public void load() {
        modelsFolder.mkdirs();
        try {
            rooms.load();
            arenas.load();
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Error loading models!", ex);
        }
    }

    public void save() {
        modelsFolder.mkdirs();
        try {
            rooms.save();
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

    public Rooms getRooms() {
        return rooms;
    }

    public Arenas getArenas() {
        return arenas;
    }
}
