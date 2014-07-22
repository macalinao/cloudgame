/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.events;

import com.simplyian.cloudgame.game.Game;
import org.bukkit.event.HandlerList;

/**
 * Called when a player quits the game.
 *
 * @author ian
 */
public class GameStopEvent extends GameEvent {

    private static final HandlerList handlers = new HandlerList();

    public GameStopEvent(Game<?> game) {
        super(game);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
