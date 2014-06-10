/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.game;

import java.util.HashSet;
import java.util.Set;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.model.room.Room;
import org.bukkit.Location;

/**
 *
 * @author ian
 */
public class GameManager {

    private final MattGame plugin;

    private final Set<Game> games = new HashSet<>();

    public GameManager(MattGame plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets the game at the given room.
     *
     * @param r
     * @return
     */
    public Game gameAt(Room r) {
        for (Game game : games) {
            if (game.getArena().getLobby().equals(r) || game.getArena().getMain().equals(r)) {
                return game;
            }
        }
        return null;
    }
}
