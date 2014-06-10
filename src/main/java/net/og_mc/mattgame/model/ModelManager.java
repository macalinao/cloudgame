/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model;

import java.io.File;
import net.og_mc.mattgame.MattGame;

/**
 *
 * @author ian
 */
public class ModelManager {

    private final MattGame plugin;

    private final File modelsFolder;

    public ModelManager(MattGame plugin) {
        this.plugin = plugin;
        this.modelsFolder = new File(plugin.getDataFolder(), "models/");
    }

    public MattGame getPlugin() {
        return plugin;
    }

    public File getModelsFolder() {
        return modelsFolder;
    }
}
