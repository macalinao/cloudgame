/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.game;

import net.og_mc.mattgame.gameplay.Gameplay;
import net.og_mc.mattgame.gameplay.State;
import net.og_mc.mattgame.model.arena.Arena;

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

}
