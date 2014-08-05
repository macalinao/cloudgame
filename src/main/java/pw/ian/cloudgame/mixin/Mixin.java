/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixin;

import java.util.HashSet;
import java.util.Set;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.GameListener;
import pw.ian.cloudgame.gameplay.Gameplay;

/**
 * A set of behaviors that can be applied to a Gameplay.
 */
public abstract class Mixin extends GameListener {

    private Set<Class<? extends State>> states = new HashSet<>();

    public Mixin(Gameplay gameplay) {
        super(gameplay);
    }

    /**
     * Sets up the Mixin. This could register listeners, register states, or do
     * whatever. This method is called during Gameplay#setup().
     */
    public void setup() {
    }

    /**
     * Registers a state with the Gameplay.
     *
     * @param clazz
     */
    protected void state(Class<? extends State> clazz) {
        states.add(clazz);
    }

    /**
     * Applies all states of this mixin to a state.
     *
     * @param game
     */
    public void applyStates(Game game) {
        for (Class<? extends State> state : states) {
            game.newState(state).withGame(game);
        }
    }

}
