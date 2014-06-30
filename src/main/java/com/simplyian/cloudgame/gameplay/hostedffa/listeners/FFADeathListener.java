/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.hostedffa.listeners;

import com.simplyian.cloudgame.events.GameQuitEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFA;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFAState;
import com.simplyian.cloudgame.gameplay.GameListener;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 *
 * @author ian
 */
public class FFADeathListener extends GameListener<HostedFFAState> {

    public FFADeathListener(HostedFFA koth) {
        super(koth);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Game<HostedFFAState> game = game(p);
        if (game == null || !game.getState().isStarted()) {
            return;
        }

        Bukkit.getPluginManager().callEvent(new GameQuitEvent(game, e.getEntity()));
    }
}
