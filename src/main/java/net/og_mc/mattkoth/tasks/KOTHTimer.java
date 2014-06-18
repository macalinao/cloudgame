/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.tasks;

import com.simplyian.cloudgame.events.GameEndEvent;
import com.simplyian.cloudgame.game.Game;
import net.og_mc.mattkoth.KOTHState;
import org.bukkit.Bukkit;
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

        if (game.getState().secondsCaptured() > 120) {
            Bukkit.getPluginManager().callEvent(new GameEndEvent(game));
            return;
        }

        if (game.getState().remainingTime() <= 0 // Should the game end?
                && game.getState().secondsCaptured() != -1) { // Overtime check
            Bukkit.getPluginManager().callEvent(new GameEndEvent(game));
        }
    }

    private void announceTimeLeft() {
        int halfMins = game.getState().remainingTime() / 30;
        int mins = halfMins / 2;
        boolean halfMin = halfMins % 2 == 1;

        game.broadcast("There are " + mins + " minutes " + (halfMin ? "and 30 seconds " : "") + "left!");
        // TODO do what matt wants
    }
}
