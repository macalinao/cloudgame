package com.simplyian.cloudgame.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.simplyian.cloudgame.game.Game;

public abstract class Timer<T extends State> extends BukkitRunnable {
    private final Game<T> game;

    private final Map<Integer, String> messages;

    private final List<Integer> times;

    private final long start;

    private final int length;

    private int current = 0;

    public Timer(Game<T> game, Map<Integer, String> messages) {
        this.game = game;
        this.messages = messages;

        times = new ArrayList<>(messages.keySet());
        length = times.get(times.size() - 1);
        start = System.currentTimeMillis();
    }

    @Override
    public void run() {
        if (game.getState().isStarted() || game.getState().isOver()) {
            cancel();
            return;
        }

        // Expect that the times are in length order
        int secsRemaining = length - (((int) (System.currentTimeMillis() - start)) / 1000);

        if (secsRemaining <= 0) {
            onEnd();
            cancel();
        } else if (secsRemaining <= times.get(current)) {
            announceTime(messages.get(current));
            current++;
        }
    }

    public void onEnd() {
    }

    /**
     * Default announcement message. Can be overriden in subclasses
     * 
     * @param time
     */
    protected void announceTime(String time) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            game.getGameplay().sendBanner(p,
                    "A " + game.getGameplay().getName() + " on map $D" + game.getArena().getName() + " $Lis starting in $D" + time + "$L!",
                    "Type $D/" + game.getGameplay().getId() + " join $Lto join $D" + game.getState().getPlayers().size() + " $Lother players! ");
        }
    }
}
