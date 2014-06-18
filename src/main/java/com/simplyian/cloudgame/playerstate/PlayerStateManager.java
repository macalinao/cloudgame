/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.playerstate;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.util.InventoryUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.ConfigurationSection;
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

    private File statesFile;

    private Map<UUID, PlayerState> states = new HashMap<>();

    public PlayerStateManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    public void load() {
        states = new HashMap<>();

        statesFile = new File(plugin.getDataFolder(), "states.yml");
        if (!statesFile.exists()) {
            return;
        }

        YamlConfiguration store = YamlConfiguration.loadConfiguration(statesFile);
        for (String uuids : store.getKeys(false)) {
            ConfigurationSection sect = store.getConfigurationSection(uuids);

            float xp = (float) sect.getDouble("xp", 0);
            ItemStack[] main = InventoryUtils.loadSection(sect.getConfigurationSection("main"), 36);
            ItemStack[] armor = InventoryUtils.loadSection(sect.getConfigurationSection("armor"), 4);

            PlayerState inv = new PlayerState(xp, main, armor);
            states.put(UUID.fromString(uuids), inv);
        }
    }

    public void save() {
        try {
            statesFile.getParentFile().mkdirs();
            statesFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(PlayerStateManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        YamlConfiguration store = new YamlConfiguration();

        for (Entry<UUID, PlayerState> inv : states.entrySet()) {
            ConfigurationSection sect = store.createSection(inv.getKey().toString());

            PlayerState state = inv.getValue();
            sect.set("xp", state.getXp());
            InventoryUtils.saveSection(sect.createSection("main"), state.getMain());
            InventoryUtils.saveSection(sect.createSection("armor"), state.getArmor());
        }

        try {
            store.save(statesFile);
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
