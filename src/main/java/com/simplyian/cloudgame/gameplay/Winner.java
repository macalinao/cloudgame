package com.simplyian.cloudgame.gameplay;


import com.simplyian.cloudgame.game.Game;

public abstract class Winner<T extends State> {

    private final Game<T> game;

    public Winner(Game<T> game) {
        this.game = game;
    }

    public Game<T> getGame() {
        return game;
    }
   
    public abstract void sendMessage(String message);

}
