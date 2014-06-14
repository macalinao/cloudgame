/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.gameplay.states.FFAState;

/**
 *
 * @author ian
 */
public class KOTHState extends FFAState {

    private boolean started = false;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

}
