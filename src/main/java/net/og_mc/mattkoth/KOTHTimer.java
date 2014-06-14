/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.game.Game;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author ian
 */
public class KOTHTimer extends BukkitRunnable {

    private final Game<KOTHState> game;

    private final long start = System.currentTimeMillis();

    public KOTHTimer(Game<KOTHState> game) {
        this.game = game;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
