package com.simplyian.cloudgame.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.simplyian.cloudgame.game.Game;
import java.util.Collections;

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
        Collections.sort(times);
        length = times.get(times.size() - 1);
        start = System.currentTimeMillis();
        current = times.size() - 1;
    }

    @Override
    public void run() {
        if (game.getState().isStarted() || game.getState().isOver()) {
            cancel();
            return;
        }

        // Expect that the times are in length order
        int secsRemaining = length - (((int) (System.currentTimeMillis() - start)) / 1000);

        int nextSecs = times.get(current);
        if (secsRemaining <= 0) {
            onEnd();
            cancel();
        } else if (secsRemaining <= nextSecs) {
            onCheckpoint(nextSecs, messages.get(nextSecs));
            current--;
        }
    }

    public void onCheckpoint(int seconds, String time) {
    }

    public void onEnd() {
    }
}
