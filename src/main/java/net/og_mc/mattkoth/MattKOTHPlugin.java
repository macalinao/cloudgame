/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.CloudGame;

/**
 *
 * @author ian
 */
public class MattKOTHPlugin extends CloudGame {

    @Override
    public void addGameplays() {
        addGameplay(new MattKOTH(this));
    }

}
