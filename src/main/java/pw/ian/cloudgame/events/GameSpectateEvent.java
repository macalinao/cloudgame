/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.events;

import pw.ian.cloudgame.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when a player spectates the game.
 *
 * @author ian
 */
public class GameSpectateEvent extends GamePlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    public GameSpectateEvent(Game<?> game, Player player) {
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
