/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.hostedffa.listeners;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFA;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFAState;
import com.simplyian.cloudgame.gameplay.GameListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @author ian
 */
public class FFACommandListener extends GameListener<HostedFFAState> {

    public FFACommandListener(HostedFFA koth) {
        super(koth);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Game<HostedFFAState> game = game(e.getPlayer());
        if (game != null && game.getState().isStarted() && !e.getMessage().startsWith("/" + getGameplay().getId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpectatorCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Game<HostedFFAState> game = gameSpectated(e.getPlayer());
        if (game != null && game.getState().isStarted()
                && !e.getMessage().startsWith("/" + getGameplay().getId()) && !e.getMessage().startsWith("/msg") && !e.getMessage().startsWith("/r ")) {
            e.setCancelled(true);
        }
    }

}
