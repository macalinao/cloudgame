/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.logic;

/**
 *
 * @author ian
 * @param <T>
 */
public abstract class Logic<T extends State> {

    public abstract T newState();

}
