/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.listeners;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.State;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

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
    public void onSpectatorInteract(PlayerInteractEvent e) {
        Game<T> game = gameSpectated(e.getPlayer());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

}
