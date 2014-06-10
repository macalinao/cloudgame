/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.logic;

/**
 * Contains all game logic.
 *
 * @author ian
 * @param <T> The type of state associated with this game.
 */
public abstract class Logic<T extends State> {

    /**
     * Creates a new state associated with this game logic.
     *
     * @return
     */
    public abstract T newState();

}
