/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.events;

import com.simplyian.cloudgame.game.Game;
import org.bukkit.event.Event;

/**
 *
 * @author ian
 */
public abstract class GameEvent extends Event {

    private final Game game;

    public GameEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

}
