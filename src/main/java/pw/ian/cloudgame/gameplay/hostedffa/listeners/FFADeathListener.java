/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import pw.ian.cloudgame.events.GameQuitEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.GameListener;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAState;

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
