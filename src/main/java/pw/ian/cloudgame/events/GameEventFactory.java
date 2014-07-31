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

    private final Game<?> game;

    /**
     * C'tor
     *
     * @param game
     */
    public GameEventFactory(Game<?> game) {
        this.game = game;
    }

    /**
     * Game end event
     *
     * @param w
     */
    public void end(Winner w) {
        Bukkit.getPluginManager().callEvent(new GameEndEvent(game, w));
    }

    /**
     * Game join event
     *
     * @param p
     */
    public void join(Player p) {
        Bukkit.getPluginManager().callEvent(new GameJoinEvent(game, p));
    }

    /**
     * Game leave event
     *
     * @param p
     */
    public void leave(Player p) {
        Bukkit.getPluginManager().callEvent(new GameLeaveEvent(game, p));
    }

    /**
     * Game quit event
     *
     * @param p
     */
    public void quit(Player p) {
        Bukkit.getPluginManager().callEvent(new GameQuitEvent(game, p));
    }

    /**
     * Game spectate event
     *
     * @param p
     */
    public void spectate(Player p) {
        Bukkit.getPluginManager().callEvent(new GameSpectateEvent(game, p));
    }

    /**
     * Game start event
     */
    public void start() {
        Bukkit.getPluginManager().callEvent(new GameStartEvent(game));
    }

    /**
     * Game stop event
     */
    public void stop() {
        Bukkit.getPluginManager().callEvent(new GameStopEvent(game));
    }

    /**
     * Game unspectate event
     *
     * @param p
     */
    public void unspectate(Player p) {
        Bukkit.getPluginManager().callEvent(new GameUnspectateEvent(game, p));
    }
}
