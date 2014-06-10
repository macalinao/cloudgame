/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.game;

import java.util.HashMap;
import java.util.Map;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.logic.Logic;
import net.og_mc.mattgame.logic.State;
import net.og_mc.mattgame.model.arena.Arena;
import net.og_mc.mattgame.model.room.Room;

/**
 *
 * @author ian
 */
public class GameManager {

    private final MattGame plugin;

    private final Map<Arena, Game> games = new HashMap<>();

    public GameManager(MattGame plugin) {
        this.plugin = plugin;
    }

    /**
     * Creates a game at the given arena.
     *
     * @param <T>
     * @param logic
     * @param arena
     * @return
     */
    public <T extends State> Game<T> createGame(Logic<T> logic, Arena arena) {
        Game<T> game = new Game<>(logic, arena);
        games.put(arena, game);
        return game;
    }

    /**
     * Gets the game at the given room.
     *
     * @param r
     * @return
     */
    public Game gameAt(Room r) {
        Arena a = plugin.getModelManager().getArenas().findByRoom(r);
        if (a == null) {
            return null;
        }
        return games.get(a);
    }
}
