/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.tasks;

import com.simplyian.cloudgame.game.Game;
import net.og_mc.mattkoth.KOTHState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author ian
 */
public class KOTHTimer extends BukkitRunnable {

    private final Game<KOTHState> game;

    public KOTHTimer(Game<KOTHState> game) {
        this.game = game;
    }

    @Override
    public void run() {
        announceTimeLeft();
    }

    private void announceTimeLeft() {
        int halfMins = game.getState().remainingTime() / 30;
        int mins = halfMins / 2;
        boolean halfMin = halfMins % 2 == 1;

        for (Player p : game.getState().getPlayers()) {
            game.getGameplay().sendGameMessage(p, "There are " + mins + " minutes " + (halfMin ? "and 30 seconds " : "") + "left!");
        }
    }
}
