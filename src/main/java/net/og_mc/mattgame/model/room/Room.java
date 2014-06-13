/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model.room;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.og_mc.mattgame.model.Model;
import org.bukkit.World;

/**
 *
 * @author ian
 */
public class Room extends Model {

    private final World world;

    private final ProtectedRegion region;

    Room(String id, World world, ProtectedRegion region) {
        super(id);
        this.world = world;
        this.region = region;
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
}
