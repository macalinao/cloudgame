/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.events;

import org.bukkit.entity.Player;
import pw.ian.cloudgame.game.Game;

/**
 * Called when a player quits the game.
 *
 * @author ian
 */
public abstract class GamePlayerEvent extends GameEvent {

    private final Player player;

    protected GamePlayerEvent(Game<?> game, Player player) {
        super(game);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
