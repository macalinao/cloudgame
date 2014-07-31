/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.events;

import org.bukkit.event.HandlerList;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Winner;

/**
 *
 * @author ian
 */
public class GameEndEvent extends GameEvent {

    private static final HandlerList handlers = new HandlerList();

    private final Winner<?> winner;

    GameEndEvent(Game<?> game, Winner<?> winner) {
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
