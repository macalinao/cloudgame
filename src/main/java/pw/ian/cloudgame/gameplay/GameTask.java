/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay;

import org.bukkit.scheduler.BukkitRunnable;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.states.Status;

/**
 *
 * @author ian
 * @param <T>
 */
public abstract class GameTask<T extends Participants> {

    private final Game game;

    private GameTaskBukkitRunnable task;

    public GameTask(Game game) {
        this.game = game;
        this.task = new GameTaskBukkitRunnable();
    }

    public void runTimer() {
        runTimer(2L);
    }

    public void runTimer(long interval) {
        task.runTaskTimer(game.getGameplay().getPlugin(), 0L, interval);
    }

    protected void cancel() {
        task.cancel();
        task = null;
    }

    public abstract void run();

    private class GameTaskBukkitRunnable extends BukkitRunnable {

        @Override
        public void run() {
            if (GameTask.this.game.state(Status.class).isOver()) {
                cancel();
                return;
            }

            GameTask.this.run();
        }

    }
}
