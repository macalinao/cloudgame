/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.events;

import org.bukkit.Bukkit;
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

    public static final void callGameEndEvent(Game<?> game, Winner w) {
        Bukkit.getPluginManager().callEvent(new GameEndEvent(game, w));
    }
}
