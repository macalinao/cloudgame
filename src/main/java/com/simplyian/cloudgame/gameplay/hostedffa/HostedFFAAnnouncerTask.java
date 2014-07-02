/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.hostedffa;

import com.simplyian.cloudgame.events.GameStartEvent;
import com.simplyian.cloudgame.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Announces the game and starts it when the time is up.
 *
 * @param <T>
 */
public class HostedFFAAnnouncerTask<T extends HostedFFAState> extends BukkitRunnable {

    private final Game<T> game;

    private final long start = System.currentTimeMillis();

    private int announceCount = 0;

    public HostedFFAAnnouncerTask(Game<T> game) {
        this.game = game;
    }

    @Override
    public void run() {
        if (game.getState().isStarted() || game.getState().isOver()) {
            cancel();
            return;
        }

        int secsLeft = 5 * 60 - (((int) (System.currentTimeMillis() - start)) / 1000);

        if (secsLeft <= 5 * 60 && announceCount == 0) {
            announceTime("5 minutes");
            announceCount++;
        } else if (secsLeft <= 3 * 60 && announceCount == 1) {
            announceTime("3 minutes");
            announceCount++;
        } else if (secsLeft <= 1 * 60 && announceCount == 2) {
            announceTime("1 minute");
            announceCount++;
        } else if (secsLeft <= 30 && announceCount == 3) {
            announceTime("30 seconds");
            announceCount++;
        } else if (secsLeft <= 10 && announceCount == 4) {
            announceTime("10 seconds");
            announceCount++;
        } else if (secsLeft <= 0 && announceCount == 5) {
            Bukkit.getPluginManager().callEvent(new GameStartEvent(game));
            cancel();
        }
    }

    private void announceTime(String time) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            game.getGameplay().sendBanner(p,
                    "A " + game.getGameplay().getName() + " on map $D" + game.getArena().getName() + " "
                    + "$Lis starting in $D" + time + "$L!",
                    "Type $D/" + game.getGameplay().getId() + " join $Lto join $D"
                    + game.getState().getPlayers().size() + " $Lother players! (armor " + (game.getState().isEasy() ? "" : "not ") + "provided)");
        }
    }

}
