/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixin;

import pw.ian.cloudgame.gameplay.GameListener;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.State;

/**
 * A set of behaviors that can be applied to a Gameplay.
 *
 * @param <T> The type of the State
 */
public abstract class Mixin<T extends State> extends GameListener<T> {

    public Mixin(Gameplay<T> gameplay) {
        super(gameplay);
    }

    /**
     * Sets up the Mixin. This could register listeners, register states, or do
     * whatever. This method is called during Gameplay#setup().
     */
    public void setup() {
    }

}
