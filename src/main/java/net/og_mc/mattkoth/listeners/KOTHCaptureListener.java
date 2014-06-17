/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.listeners;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.GameListener;
import com.simplyian.cloudgame.model.region.Region;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.MattKOTH;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author ian
 */
public class KOTHCaptureListener extends GameListener<KOTHState> {

    public KOTHCaptureListener(MattKOTH koth) {
        super(koth);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInitialCapture(PlayerMoveEvent e) {
        Game<KOTHState> game = game(e.getPlayer());
        if (game == null || !game.getState().isStarted() || (game.getState().getCapturer() != null)) {
            return;
        }

        if (e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            return;
        }

        Region hill = getGameplay().getPlugin().getModelManager().getRegions().findById(game.getArena().getProperty("koth.hill").toString());
        if (!hill.contains(e.getTo())) {
            return;
        }

        // Initial capture!
        game.getState().setCapturer(e.getPlayer());
        game.broadcast(e.getPlayer().getName() + " has taken control of the hill!");
    }
}
