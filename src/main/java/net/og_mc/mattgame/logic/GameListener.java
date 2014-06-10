/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.logic;

import org.bukkit.event.Listener;

/**
 *
 * @author ian
 */
public class GameListener implements Listener {

    private final Logic logic;

    public GameListener(Logic logic) {
        this.logic = logic;
    }
}
