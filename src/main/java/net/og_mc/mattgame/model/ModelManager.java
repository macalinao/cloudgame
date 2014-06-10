/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.model.arena.Arenas;
import net.og_mc.mattgame.model.room.Rooms;

/**
 *
 * @author ian
 */
public class ModelManager {

    private final MattGame plugin;

    private final File modelsFolder;

    private final Rooms rooms;

    private final Arenas arenas;

    public ModelManager(MattGame plugin) {
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

    public MattGame getPlugin() {
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
