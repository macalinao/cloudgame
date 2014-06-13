/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.game;

import java.util.HashMap;
import java.util.Map;
import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.State;
import com.simplyian.cloudgame.model.arena.Arena;
import com.simplyian.cloudgame.model.room.Room;

/**
 *
 * @author ian
 */
public class GameManager {

    private final CloudGame plugin;

    private final Map<Arena, Game> games = new HashMap<>();

    public GameManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    /**
     * Creates a game at the given arena.
     *
     * @param <T>
     * @param gameplay
     * @param arena
     * @return
     */
    public <T extends State> Game<T> createGame(Gameplay<T> gameplay, Arena arena) {
        Game<T> game = new Game<>(gameplay, arena);
        games.put(arena, game);
        gameplay.setup(game);
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
