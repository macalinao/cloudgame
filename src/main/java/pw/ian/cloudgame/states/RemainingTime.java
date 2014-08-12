/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.states;

import pw.ian.cloudgame.gameplay.State;

/**
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
