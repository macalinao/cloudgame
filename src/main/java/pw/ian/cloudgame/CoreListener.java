/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pw.ian.cloudgame.model.arena.Arena;

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

        final String spawnName = e.getPlayer().getName();
        (new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + spawnName);
            }
        }).runTaskLater(plugin, 1L);
    }
}
