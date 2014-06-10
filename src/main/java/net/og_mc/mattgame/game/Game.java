/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.game;

import net.og_mc.mattgame.logic.Logic;
import net.og_mc.mattgame.logic.State;
import net.og_mc.mattgame.model.arena.Arena;

/**
 *
 * @author ian
 */
public class Game {

    private final Logic<?> logic;

    private final Arena arena;

    private final State state;

    public Game(Logic logic, Arena arena) {
        this.logic = logic;
        this.arena = arena;
        state = logic.newState();
    }

    public Logic getLogic() {
        return logic;
    }

    public Arena getArena() {
        return arena;
    }

    public State getState() {
        return state;
    }

}
