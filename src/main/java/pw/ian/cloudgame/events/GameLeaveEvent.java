/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import pw.ian.cloudgame.game.Game;

/**
 * Called when a player quits the game.
 *
 * @author ian
 */
public class GameLeaveEvent extends GamePlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    GameLeaveEvent(Game game, Player player) {
        super(game, player);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
