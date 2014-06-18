/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.playerstate;

import com.simplyian.cloudgame.CloudGame;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Stores inventories when players enter the arena.
 *
 * @author simplyianm
 */
public class PlayerStateManager {

    private final CloudGame plugin;

    private File invsFile;

    private Map<UUID, PlayerState> states = new HashMap<>();

    public PlayerStateManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    public void load() {
        states = new HashMap<>();

        invsFile = new File(plugin.getDataFolder(), "invs.yml");
        if (!invsFile.exists()) {
            return;
        }

        YamlConfiguration store = YamlConfiguration.loadConfiguration(invsFile);
        for (String uuids : store.getKeys(false)) {
            PlayerState inv = new PlayerState(store.getConfigurationSection(uuids));
            states.put(UUID.fromString(uuids), inv);
        }
    }

    public void save() {
        try {
            invsFile.getParentFile().mkdirs();
            invsFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(PlayerStateManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        YamlConfiguration store = new YamlConfiguration();

        for (Entry<UUID, PlayerState> inv : states.entrySet()) {
            inv.getValue().save(store.createSection(inv.getKey().toString()));
        }

        try {
            store.save(invsFile);
        } catch (IOException ex) {
            Logger.getLogger(PlayerStateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads a player's saved state.
     *
     * @param p
     */
    public void loadState(Player p) {
        PlayerState is = states.get(p.getUniqueId());
        if (is == null) {
            return;
        }

        is.restore(p);
        states.remove(p.getUniqueId());
    }

    /**
     * Saves the current state of a player.
     *
     * @param p
     */
    public void saveState(Player p) {
        PlayerInventory i = p.getInventory();
        states.put(p.getUniqueId(), new PlayerState(p));
        i.clear();
        i.setArmorContents(new ItemStack[4]);
    }
}
