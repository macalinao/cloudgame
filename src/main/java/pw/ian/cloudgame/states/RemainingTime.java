/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.states;

import pw.ian.cloudgame.gameplay.State;

/**
 * A game {@link State} which sets a time limit on the game, providing a simple
 * check for the amount of time the game has remaining
 *
 * @author ian
 */
public class RemainingTime extends State {

    private int mins;

    public int getMins() {
        return mins;
    }

    public RemainingTime setMins(int mins) {
        this.mins = mins;
        return this;
    }

    public int remainingTime() {
        int secsElapsed = (((int) (System.currentTimeMillis() - game.state(Status.class).getStart())) / 1000);
        return (mins * 60) - secsElapsed;
    }
}
