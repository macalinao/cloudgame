/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.playerstate;

import com.simplyian.cloudgame.CloudGame;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
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

    private final Map<UUID, PlayerState> states = new HashMap<>();

    private final Set<UUID> stateRestoreQueue = new HashSet<>();

    public PlayerStateManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    /**
     * Sets up the task
     */
    public void setupStateRestoreQueueProcessTask() {
        (new PlayerStateRestoreQueueProcessTask(this)).runTaskTimer(plugin, 1L, 1L);
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

    /**
     * Adds a player to the state restore queue.
     *
     * @param player
     */
    public void addToStateRestoreQueue(Player player) {
        stateRestoreQueue.add(player.getUniqueId());
    }

    /**
     * Processes the state restore queue. Should be executed every tick.
     */
    public void processStateRestoreQueue() {
        Iterator<UUID> it = stateRestoreQueue.iterator();
        while (it.hasNext()) {
            UUID uuid = it.next();
            Player p = Bukkit.getPlayer(uuid);
            if (p != null && p.isValid()) { // Check if alive
                loadState(p);
                it.remove();
            }
        }
    }
}
