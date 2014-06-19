/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.listeners;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.listeners.GameListener;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.MattKOTH;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 *
 * @author ian
 */
public class KOTHRespawnListener extends GameListener<KOTHState> {

    public KOTHRespawnListener(MattKOTH koth) {
        super(koth);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Game<KOTHState> game = game(e.getEntity());
        if (game == null || !game.getState().isStarted()) {
            return;
        }

        e.getEntity().teleport(game.getArena().getNextSpawn());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Game<KOTHState> game = game(e.getPlayer());
        if (game == null || !game.getState().isStarted()) {
            return;
        }

        e.setRespawnLocation(game.getArena().getNextSpawn());
    }
}
