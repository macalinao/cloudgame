/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author ian
 */
public class InventoryUtils {

    private InventoryUtils() {

    }

    /**
     * Loads a configuration section.
     *
     * @param store
     * @param length
     * @return
     */
    public static ItemStack[] loadSection(ConfigurationSection store, int length) {
        ItemStack[] ret = new ItemStack[length];
        if (store == null) {
            return ret;
        }

        for (String iid : store.getKeys(false)) {
            int id = Integer.parseInt(iid);
            ret[id] = (ItemStack) store.getItemStack(iid);
        }

        return ret;
    }

    /**
     * Saves a configuration section to the given config.
     *
     * @param store
     * @param contents
     */
    public static void saveSection(ConfigurationSection store, ItemStack[] contents) {
        for (int i = 0; i < contents.length; i++) {
            store.set(Integer.toString(i), contents[i]);
        }
    }
}
