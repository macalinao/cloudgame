/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.listeners;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.GameListener;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.MattKOTH;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @author ian
 */
public class KOTHCommandListener extends GameListener<KOTHState> {

    public KOTHCommandListener(MattKOTH koth) {
        super(koth);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Game<KOTHState> game = game(e.getPlayer());
        if (game != null && !e.getMessage().startsWith("/koth")) {
            e.setCancelled(true);
        }
    }

}
