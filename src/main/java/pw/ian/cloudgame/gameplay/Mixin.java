/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import pw.ian.cloudgame.game.Game;

/**
 * A set of behaviors that can be applied to a Gameplay.
 */
public abstract class Mixin extends GameListener {

    /**
     * The dependencies of this mixin..
     */
    private final Set<Class<? extends Mixin>> dependencies = new LinkedHashSet<>();

    /**
     * The states that this mixin provides.
     */
    private final Set<Class<? extends State>> states = new HashSet<>();

    /**
     * Constructor
     *
     * @param gameplay
     */
    protected Mixin(Gameplay gameplay) {
        super(gameplay);
    }

    /**
     * Resolves the dependencies of this Mixin.
     */
    public void resolveDependencies() {
        for (Class<? extends Mixin> dep : dependencies) {
            if (gameplay.hasMixin(dep)) {
                continue;
            }
            gameplay.mixin(dep);
        }
    }

    /**
     * Sets up the Mixin. This is called after dependencies are resolved.
     */
    public void setup() {
    }

    /**
     * Registers a dependency with this Mixin.
     *
     * @param clazz
     */
    protected void depend(Class<? extends Mixin> clazz) {
        dependencies.add(clazz);
    }

    /**
     * Registers a state with this Mixin.
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
