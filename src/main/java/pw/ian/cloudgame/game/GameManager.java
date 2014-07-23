/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.game;

import java.util.HashMap;
import java.util.Map;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.events.GameStopEvent;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.State;
import pw.ian.cloudgame.model.arena.Arena;
import pw.ian.cloudgame.model.region.Region;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class GameManager {

    private final CloudGame plugin;

    private final Map<Arena, Game<?>> games = new HashMap<>();

    public GameManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    /**
     * Creates a game at the given arena.
     *
     * @param <T>
     * @param gameplay
     * @param arena
     * @return Null if the game could not be created due to canUse restrictions.
     */
    public <T extends State> Game<T> createGame(Gameplay<T> gameplay, Arena arena) {
        if (!gameplay.canUse(arena)) {
            return null;
        }

        if (gameAt(arena) != null) {
            return null;
        }

        Game<T> game = new Game<>(gameplay, arena);
        games.put(arena, game);
        gameplay.setup(game);
        return game;
    }

    /**
     * Removes a game from the game manager.
     *
     * @param game
     */
    public void removeGame(Game<?> game) {
        games.remove(game.getArena());
    }

    /**
     * Gets the game at the given arena.
     *
     * @param a
     * @return
     */
    public Game gameAt(Arena a) {
        return games.get(a);
    }

    /**
     * Gets the game at the given region.
     *
     * @param r
     * @return
     */
    public Game gameAt(Region r) {
        Arena a = plugin.getModelManager().getArenas().findByRegion(r);
        if (a == null) {
            return null;
        }
        return gameAt(a);
    }

    /**
     * Gets the spectated game of the given player.
     *
     * @param p
     * @return
     */
    public Game<?> spectatedGameOf(Player p) {
        for (Game g : games.values()) {
            if (g.getState().getSpectators().contains(p)) {
                return g;
            }
        }
        return null;
    }

    /**
     * Gets the game of the given player.
     *
     * @param p
     * @return
     */
    public Game gameOf(Player p) {
        for (Game g : games.values()) {
            if (g.getState().getPlayers().contains(p)) {
                return g;
            }
        }
        return null;
    }

    /**
     * Forces all games to stop.
     */
    public void stopAll() {
        for (Game<?> game : games.values()) {
            Bukkit.getPluginManager().callEvent(new GameStopEvent(game));
        }
        games.clear();
    }
}
