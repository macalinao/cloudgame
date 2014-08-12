/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import pw.ian.cloudgame.events.GameEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.model.region.Region;

/**
 *
 * @author ian
 */
@SuppressWarnings("ALL")
public class GameListener implements Listener {

    private final Gameplay gameplay;

    public GameListener(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    /**
     * Gets the game associated with the location.
     *
     * @param loc
     * @return
     */
    protected Game game(Location loc) {
        Region r = gameplay.getPlugin().getModelManager().getRegions().find(loc);
        if (r == null) {
            return null;
        }
        Game game = gameplay.getPlugin().getGameManager().gameAt(r);
        if (game == null) {
            return null;
        }
        if (!game.getGameplay().equals(gameplay)) {
            return null;
        }
        return game;
    }

    /**
     * Gets the game of the given player.
     *
     * @param p
     * @return
     */
    protected Game game(Player p) {
        Game game = gameplay.getPlugin().getGameManager().gameOf(p);
        if (game == null) {
            return null;
        }
        if (!game.getGameplay().equals(gameplay)) {
            return null;
        }
        return game;
    }

    /**
     * Gets the game of the given game event.
     *
     * @param g
     * @return
     */
    public Game game(GameEvent g) {
        if (!g.getGame().getGameplay().equals(gameplay)) {
            return null;
        }
        return g.getGame();
    }

    /**
     * Gets the game spectated by the given player.
     *
     * @param p
     * @return
     */
    public Game gameSpectated(Player p) {
        Game game = gameplay.getPlugin().getGameManager().spectatedGameOf(p);
        if (game == null) {
            return null;
        }
        if (!game.getGameplay().equals(gameplay)) {
            return null;
        }
        return game;
    }
}
