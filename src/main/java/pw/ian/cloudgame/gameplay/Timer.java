package pw.ian.cloudgame.gameplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import pw.ian.cloudgame.game.Game;

public abstract class Timer<T extends Participants> extends GameTask<T> {

    private final Game game;

    private final Map<Integer, String> messages;

    private final List<Integer> times;

    private final long start;

    private final int length;

    private int current = 0;

    public Timer(Game game, Map<Integer, String> messages) {
        super(game);
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
        if (game.getState().isStarted()) {
            cancel();
            return;
        }

        // Expect that the times are in length order
        int secsRemaining = length - (((int) (System.currentTimeMillis() - start)) / 1000);

        if (secsRemaining <= 0 || current < 0) {
            onEnd();
            cancel();
            return;
        }

        int nextSecs = times.get(current);
        if (secsRemaining <= nextSecs) {
            onCheckpoint(nextSecs, messages.get(nextSecs));
            current--;
        }
    }

    public abstract void onCheckpoint(int seconds, String time);

    public abstract void onEnd();
}
