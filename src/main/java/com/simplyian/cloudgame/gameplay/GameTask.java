/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import com.simplyian.cloudgame.game.Game;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author ian
 */
public abstract class GameTask<T extends State> {

    private final Game<T> game;

    public GameTask(Game<T> game) {
        this.game = game;
    }

    public void runTimer() {
        runTimer(2L);
    }

    public void runTimer(long interval) {
        (new GameTaskBukkitRunnable()).runTaskTimer(game.getGameplay().getPlugin(), 0L, interval);
    }

    public abstract void run();

    private class GameTaskBukkitRunnable extends BukkitRunnable {

        @Override
        public void run() {
            if (GameTask.this.game.getState().isOver()) {
                cancel();
                return;
            }

            GameTask.this.run();
        }

    }
}
