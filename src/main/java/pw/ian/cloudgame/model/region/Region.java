/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.model.region;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import pw.ian.cloudgame.model.Model;
import com.sk89q.worldedit.Vector;
import org.bukkit.Location;
import org.bukkit.World;

/**
 *
 * @author ian
 */
public class Region extends Model {

    private final World world;

    private final ProtectedRegion region;

    Region(String id, World world, ProtectedRegion region) {
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

    /**
     * Returns true if this region contains the given location.
     *
     * @param loc
     * @return
     */
    public boolean contains(Location loc) {
        return loc.getWorld().equals(world) && region.contains(new Vector(loc.getX(), loc.getY(), loc.getZ()));
    }
}
