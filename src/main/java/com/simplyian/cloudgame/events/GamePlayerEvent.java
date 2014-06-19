/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.events;

import com.simplyian.cloudgame.game.Game;
import org.bukkit.entity.Player;

/**
 * Called when a player quits the game.
 *
 * @author ian
 */
public abstract class GamePlayerEvent extends GameEvent {

    private final Player player;

    public GamePlayerEvent(Game<?> game, Player player) {
        super(game);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
