/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model.room;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.World;

/**
 *
 * @author ian
 */
public class Room {

    private final String id;

    private final World world;

    private final ProtectedRegion region;

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

}