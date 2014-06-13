/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.inventory;

import com.simplyian.cloudgame.CloudGame;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
public class InventoryManager {

    private final CloudGame plugin;

    private File invsFile;

    private Map<String, InventoryStore> invs = new HashMap<>();

    public InventoryManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    public void load() {
        invs = new HashMap<>();

        invsFile = new File(plugin.getDataFolder(), "invs.yml");
        if (!invsFile.exists()) {
            return;
        }

        YamlConfiguration store = YamlConfiguration.loadConfiguration(invsFile);
        for (String pn : store.getKeys(false)) {
            InventoryStore inv = new InventoryStore(store.getConfigurationSection(pn));
            invs.put(pn, inv);
        }
    }

    public void save() {
        try {
            invsFile.getParentFile().mkdirs();
            invsFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(InventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        YamlConfiguration store = new YamlConfiguration();

        for (Entry<String, InventoryStore> inv : invs.entrySet()) {
            inv.getValue().save(store.createSection(inv.getKey()));
        }

        try {
            store.save(invsFile);
        } catch (IOException ex) {
            Logger.getLogger(InventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void backupInventory(Player p) {
        PlayerInventory i = p.getInventory();
        invs.put(p.getName(), new InventoryStore(p));
        i.clear();
        i.setArmorContents(new ItemStack[4]);
    }

    public void restoreInventory(Player p) {
        InventoryStore is = invs.get(p.getName());
        if (is == null) {
            p.getInventory().clear();
        } else {
            is.restore(p);
        }
        invs.remove(p.getName());
    }
}
