/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.events;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Winner;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 *
 * @author ian
 */
public class GameEndEvent extends GameEvent {

    private static final HandlerList handlers = new HandlerList();

    private final Winner<?> winner;

    public GameEndEvent(Game<?> game, Winner<?> winner) {
        super(game);
        this.winner = winner;
    }

    public Winner<?> getWinner() {
        return winner;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
