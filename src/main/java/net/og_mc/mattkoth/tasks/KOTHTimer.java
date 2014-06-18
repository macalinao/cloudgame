/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.tasks;

import com.simplyian.cloudgame.events.GameEndEvent;
import com.simplyian.cloudgame.events.GameStartEvent;
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

    private int announceCount = 0;

    public KOTHTimer(Game<KOTHState> game) {
        this.game = game;
    }

    @Override
    public void run() {
        announceTimeLeft();

        if (game.getState().secondsCaptured() > 120) {
            Bukkit.getPluginManager().callEvent(new GameEndEvent(game));
            cancel();
            return;
        }

        if (game.getState().remainingTime() <= 0 // Should the game end?
                && game.getState().secondsCaptured() != -1) { // Overtime check
            Bukkit.getPluginManager().callEvent(new GameEndEvent(game));
            cancel();
        }
    }

    private void announceTimeLeft() {
        int secsLeft = game.getState().remainingTime();
        if (secsLeft <= 7 * 60 && announceCount == 0) {
            announceTime("7 minutes");
            announceCount++;
        } else if (secsLeft <= 5 * 60 && announceCount == 1) {
            announceTime("5 minutes");
            announceCount++;
        } else if (secsLeft <= 3 * 60 && announceCount == 2) {
            announceTime("3 minute");
            announceCount++;
        } else if (secsLeft <= 1 * 60 && announceCount == 3) {
            announceTime("1 minute");
            announceCount++;
        }
    }

    private void announceTime(String time) {
        game.broadcast("There's " + time + " left!");
    }
}
