/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Winner;

/**
 *
 * @author ian
 */
public class GameEventFactory {

    /**
     * C'tor
     */
    private GameEventFactory() {
    }

    /**
     * Game end event
     *
     * @param game
     * @param w
     */
    public static final void callGameEndEvent(Game<?> game, Winner w) {
        Bukkit.getPluginManager().callEvent(new GameEndEvent(game, w));
    }

    /**
     * Game join event
     *
     * @param game
     * @param p
     */
    public static final void callGameJoinEvent(Game<?> game, Player p) {
        Bukkit.getPluginManager().callEvent(new GameJoinEvent(game, p));
    }

    /**
     * Game leave event
     *
     * @param game
     * @param p
     */
    public static final void callGameLeaveEvent(Game<?> game, Player p) {
        Bukkit.getPluginManager().callEvent(new GameLeaveEvent(game, p));
    }

    /**
     * Game quit event
     *
     * @param game
     * @param p
     */
    public static final void callGameQuitEvent(Game<?> game, Player p) {
        Bukkit.getPluginManager().callEvent(new GameQuitEvent(game, p));
    }

    /**
     * Game spectate event
     *
     * @param game
     * @param p
     */
    public static final void callGameSpectateEvent(Game<?> game, Player p) {
        Bukkit.getPluginManager().callEvent(new GameSpectateEvent(game, p));
    }

    /**
     * Game start event
     *
     * @param game
     */
    public static final void callGameStartEvent(Game<?> game) {
        Bukkit.getPluginManager().callEvent(new GameStartEvent(game));
    }

    /**
     * Game stop event
     *
     * @param game
     */
    public static final void callGameStopEvent(Game<?> game) {
        Bukkit.getPluginManager().callEvent(new GameStopEvent(game));
    }

    /**
     * Game unspectate event
     *
     * @param game
     * @param p
     */
    public static final void callGameUnspectateEvent(Game<?> game, Player p) {
        Bukkit.getPluginManager().callEvent(new GameUnspectateEvent(game, p));
    }
}
