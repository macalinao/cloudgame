package com.simplyian.cloudgame.gameplay;

import java.util.List;
import java.util.UUID;

import com.simplyian.cloudgame.game.Game;

public class Winner<T extends State> {

    private final Game<T> game;

    private final List<UUID> winners;

    public Winner(Game<T> game, List<UUID> winners) {
        this.game = game;
        this.winners = winners;
    }

    public Game<T> getGame() {
        return game;
    }

    public List<UUID> getWinners() {
        return winners;
    }

}
