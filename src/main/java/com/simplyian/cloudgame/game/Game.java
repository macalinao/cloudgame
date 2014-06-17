/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.game;

import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.MessageHandler;
import com.simplyian.cloudgame.gameplay.State;
import com.simplyian.cloudgame.model.arena.Arena;
import com.simplyian.cloudgame.stats.Stats;
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

    private final Stats stats;

    Game(Gameplay<T> gameplay, Arena arena) {
        this.gameplay = gameplay;
        this.arena = arena;
        state = gameplay.newState();
        stats = new Stats();
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

    public Stats getStats() {
        return stats;
    }

    /**
     * Sends a message to this game.
     *
     * @param type
     * @param message
     */
    public void send(String type, Map<String, Object> message) {
        MessageHandler<T> handler = gameplay.getHandler(type);
        if (handler != null) {
            handler.onReceive(this, message);
        }
    }

}
