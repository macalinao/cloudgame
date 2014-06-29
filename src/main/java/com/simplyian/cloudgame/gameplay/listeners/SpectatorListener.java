/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.listeners;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 *
 * @author ian
 * @param <T>
 */
public class SpectatorListener<T extends State> extends GameListener<T> {

    public SpectatorListener(Gameplay<T> gameplay) {
        super(gameplay);
    }

    @EventHandler
    public void onSpectatorPickupItem(PlayerPickupItemEvent e) {
        Game<T> game = gameSpectated(e.getPlayer());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onSpectatorDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        Game<T> game = gameSpectated((Player) e.getDamager());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onSpectatorDamaged(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Game<T> game = gameSpectated((Player) e.getEntity());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onSpectatorInteract(PlayerInteractEvent e) {
        Game<T> game = gameSpectated(e.getPlayer());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

}
