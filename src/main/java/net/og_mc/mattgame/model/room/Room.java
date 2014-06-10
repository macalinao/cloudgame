/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model.room;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.model.arena.Arenas;
import org.bukkit.World;

/**
 *
 * @author ian
 */
public class Room {

    private final String id;

    private final World world;

    private final ProtectedRegion region;

    private Purpose purpose;

    Room(String id, World world, ProtectedRegion region) {
        this.id = id;
        this.world = world;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * @return the region
     */
    public ProtectedRegion getRegion() {
        return region;
    }

    /**
     * Gets the purpose of this room. Lazy-loaded.
     *
     * @return
     */
    public Purpose getPurpose() {
        if (purpose == null) {
            Arenas as = MattGame.i.getModelManager().getArenas();
            if (as.findByLobby(this) != null) {
                purpose = Purpose.ARENA_LOBBY;
            } else if (as.findByMain(this) != null) {
                purpose = Purpose.ARENA_MAIN;
            } else {
                purpose = Purpose.UNUSED;
            }
        }
        return purpose;
    }

    /**
     * Resets the purpose of this room. Internal use only.
     */
    public void resetPurpose() {
        purpose = null;
    }

    public static enum Purpose {

        ARENA_LOBBY, ARENA_MAIN, UNUSED;
    }
}
