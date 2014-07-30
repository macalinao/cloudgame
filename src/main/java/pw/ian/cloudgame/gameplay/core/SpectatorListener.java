/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pw.ian.cloudgame.events.GameUnspectateEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.GameListener;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.State;

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
    public void onSpectatorMoveOutOfArena(PlayerMoveEvent e) {
        if (e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            return;
        }

        Game<T> game = gameSpectated(e.getPlayer());
        if (game == null) {
            return;
        }

        if (game.getArena().getMain().contains(e.getTo())) {
            return;
        }

        Bukkit.getPluginManager().callEvent(new GameUnspectateEvent(game, e.getPlayer()));
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
    public void onSpectatorPotionSplash(PotionSplashEvent e) {
        for (LivingEntity ent : e.getAffectedEntities()) {
            if (!(ent instanceof Player)) {
                continue;
            }

            Game<T> game = gameSpectated((Player) ent);
            if (game == null) {
                continue;
            }

            e.setIntensity(ent, 0);
        }
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
