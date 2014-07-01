/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.events;

import com.simplyian.cloudgame.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 *
 * @author ian
 */
public class GameEndEvent extends GameEvent {

    private static final HandlerList handlers = new HandlerList();

    private final Player winner;

    public GameEndEvent(Game<?> game, Player winner) {
        super(game);
        this.winner = winner;
    }

    public Player getWinner() {
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
