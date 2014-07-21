package com.simplyian.cloudgame.gameplay;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
