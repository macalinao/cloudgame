/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.game;

import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.State;
import com.simplyian.cloudgame.model.arena.Arena;
import java.util.Map;

/**
 *
 * @author ian
 * @param <T> The type of state of this game
 */
public class Game<T extends State> {

    private final Gameplay<T> gameplay;

    private final Arena arena;

    private final T state;

    Game(Gameplay<T> gameplay, Arena arena) {
        this.gameplay = gameplay;
        this.arena = arena;
        state = gameplay.newState();
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public Arena getArena() {
        return arena;
    }

    public T getState() {
        return state;
    }

    /**
     * Sends a message to this game.
     *
     * @param type
     * @param message
     */
    public void send(String type, Map<String, Object> message) {
        gameplay.onReceive(this, type.toUpperCase(), message);
    }

}
