/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.logic;

import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.game.Game;
import net.og_mc.mattgame.model.room.Room;
import org.bukkit.Location;
import org.bukkit.event.Listener;

/**
 *
 * @author ian
 */
public class GameListener implements Listener {

    private final Logic logic;

    public GameListener(Logic logic) {
        this.logic = logic;
    }

    public Game game(Location loc) {
        Room r = MattGame.i.getModelManager().getRooms().find(loc);
        if (r == null) {
            return null;
        }
        // TOOD
        return null;
    }
}
