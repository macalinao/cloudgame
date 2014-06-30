/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import com.simplyian.cloudgame.events.GameQuitEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.stats.Death;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author ian
 * @param <T>
 */
public class CoreGameListener<T extends State> extends GameListener<T> {

    public CoreGameListener(Gameplay<T> gameplay) {
        super(gameplay);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location location = p.getLocation();
        Game<T> game = game(location); // use location in case of being queued
        if (game == null) {
            return;
        }

        UUID player = p.getUniqueId();

        UUID killer = null;
        Player killerP = p.getKiller();
        if (killerP != null) {
            killer = killerP.getUniqueId();
        }

        DamageCause cause = null;
        EntityDamageEvent lastDamageCause = e.getEntity().getLastDamageCause();
        if (lastDamageCause != null) {
            cause = lastDamageCause.getCause();
        }

        long timestamp = System.currentTimeMillis();

        Death death = new Death(player, killer, cause, timestamp, location);
        game.getStats().addDeath(death);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Game<T> g = game(e.getPlayer());
        if (g != null) {
            Bukkit.getPluginManager().callEvent(new GameQuitEvent(g, e.getPlayer()));
        }
    }

}
