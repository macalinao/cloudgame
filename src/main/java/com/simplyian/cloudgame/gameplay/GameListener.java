/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.events.GameEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.State;
import com.simplyian.cloudgame.model.region.Region;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 *
 * @author ian
 * @param <T> The type of state
 */
public class GameListener<T extends State> implements Listener {

    private final Gameplay<T> gameplay;

    public GameListener(Gameplay<T> gameplay) {
        this.gameplay = gameplay;
    }

    public Gameplay<T> getGameplay() {
        return gameplay;
    }

    /**
     * Gets the game associated with the location.
     *
     * @param loc
     * @return
     */
    public Game<T> game(Location loc) {
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
        return (Game<T>) game;
    }

    /**
     * Gets the game of the given player.
     *
     * @param p
     * @return
     */
    public Game<T> game(Player p) {
        Game game = gameplay.getPlugin().getGameManager().gameOf(p);
        if (game == null) {
            return null;
        }
        if (!game.getGameplay().equals(gameplay)) {
            return null;
        }
        return (Game<T>) game;
    }

    /**
     * Gets the game of the given game event.
     *
     * @param g
     * @return
     */
    public Game<T> game(GameEvent g) {
        if (!g.getGame().getGameplay().equals(gameplay)) {
            return null;
        }
        return (Game<T>) g.getGame();
    }

    /**
     * Gets the game spectated by the given player.
     *
     * @param p
     * @return
     */
    public Game<T> gameSpectated(Player p) {
        Game game = gameplay.getPlugin().getGameManager().spectatedGameOf(p);
        if (game == null) {
            return null;
        }
        if (!game.getGameplay().equals(gameplay)) {
            return null;
        }
        return (Game<T>) game;
    }
}
