/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.inventory;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author simplyianm
 */
public class InventoryStore {

    public float xp;
    public ItemStack[] main;
    public ItemStack[] armor;

    public InventoryStore(ConfigurationSection store) {
        load(store);
    }

    public InventoryStore(Player p) {
        xp = p.getExp();
        main = p.getInventory().getContents();
        armor = p.getInventory().getArmorContents();
    }

    public void restore(Player p) {
        p.setExp(xp);
        p.getInventory().setContents(main);
        p.getInventory().setArmorContents(armor);
    }

    public void load(ConfigurationSection store) {
        xp = (float) store.getDouble("xp", 0);
        main = loadSection(store, "main", 36);
        armor = loadSection(store, "armor", 4);
    }

    private ItemStack[] loadSection(ConfigurationSection store, String name, int length) {
        ConfigurationSection sectStore = store.getConfigurationSection(name);
        ItemStack[] ret = new ItemStack[length];
        if (sectStore == null) {
            return ret;
        }

        for (String iid : sectStore.getKeys(false)) {
            int id = Integer.parseInt(iid);
            ret[id] = (ItemStack) sectStore.getItemStack(iid);
        }

        return ret;
    }

    public void save(ConfigurationSection store) {
        store.set("xp", xp);
        saveSection(store, "main", main);
        saveSection(store, "armor", armor);
    }

    private void saveSection(ConfigurationSection store, String name, ItemStack[] contents) {
        ConfigurationSection sectStore = store.createSection(name);
        for (int i = 0; i < contents.length; i++) {
            sectStore.set(Integer.toString(i), contents[i]);
        }
    }
}
