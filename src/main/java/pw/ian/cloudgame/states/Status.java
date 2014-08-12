/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.states;

import pw.ian.cloudgame.gameplay.State;

/**
 * A {@link State} storing basic data about the current status of a game - for
 * example, when it started, and whether it is finished yet
 */
public class Status extends State {

    private long start = -1;

    private boolean over;

    public boolean isStarted() {
        return start != -1;
    }

    public void setStarted() {
        this.start = System.currentTimeMillis();
    }

    public long getStart() {
        return start;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

}
