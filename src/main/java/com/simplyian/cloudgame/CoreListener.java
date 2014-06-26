/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame;

import com.simplyian.cloudgame.model.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author ian
 */
public class CoreListener implements Listener {

    private final CloudGame plugin;

    public CoreListener(CloudGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Arena find = plugin.getModelManager().getArenas().find(e.getPlayer().getLocation());
        if (find == null) {
            return;
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + e.getPlayer().getName());
    }
}
