/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.gameplay;

import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.game.Game;
import net.og_mc.mattgame.model.room.Room;
import org.bukkit.Location;
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

    /**
     * Gets the game associated with the location.
     *
     * @param loc
     * @return
     */
    public Game game(Location loc) {
        Room r = MattGame.i.getModelManager().getRooms().find(loc);
        if (r == null) {
            return null;
        }
        Game game = MattGame.i.getGameManager().gameAt(r);
        if (game == null) {
            return null;
        }
        if (!game.getGameplay().equals(gameplay)) {
            return null;
        }
        return (Game<T>) game;
    }
}
