/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.model.region;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import static pw.ian.cloudgame.CloudGame.wg;
import pw.ian.cloudgame.model.ModelManager;
import pw.ian.cloudgame.model.Models;

/**
 * Handles the loading, saving and searching for of {@link Region} objects in
 * CloudGame
 *
 * @author ian
 * @see Models
 */
public class Regions extends Models<Region> {

    public Regions(ModelManager modelManager) {
        super(modelManager, "regions");
    }

    /**
     * Creates a new {@link Region} object in the given {@link World}, using the
     * given WorldGuard {@link ProtectedRegion} for location details
     *
     * @param world The {@link World} the {@link Region} is in
     * @param pr The {@link ProtectedRegion} the {@link Region} wraps
     * @return A new {@link Region} in the given World and ProtectedRegion
     */
    public Region create(World world, ProtectedRegion pr) {
        Region r = new Region(id(world, pr), world, pr);
        add(r);
        return r;
    }

    @Override
    public Region findById(String id) {
        Region r = super.findById(id);
        if (r == null) {
            String[] split = id.split(";");
            if (split.length != 2) {
                return null;
            }
            World world = Bukkit.getWorld(split[0]);
            if (world == null) {
                return null;
            }
            ProtectedRegion region = WorldGuardPlugin.inst().getRegionManager(world).getRegion(split[1]);
            if (region == null) {
                return null;
            }
            r = create(world, region);
        }
        return r;
    }

    public Region find(World world, ProtectedRegion pr) {
        return findById(id(world, pr));
    }

    public Region find(World world, String regionId) {
        return findById(world.getName() + ";" + regionId);
    }

    /**
     * Finds a {@link Region} from its {@link Location}.
     *
     * @param loc The {@link Location} to find the {@link Region} at
     * @return The {@link Region} at the given {@link Location}
     */
    public Region find(Location loc) {
        ApplicableRegionSet regions = wg().getRegionManager(loc.getWorld()).getApplicableRegions(loc);

        for (ProtectedRegion pr : regions) {
            Region r = find(loc.getWorld(), pr);
            if (r != null) {
                return r;
            }
        }
        return null;
    }

    public boolean remove(World world, ProtectedRegion pr) {
        return remove(id(world, pr));
    }

    @Override
    protected void load(YamlConfiguration modelsConf) {
        // no loading. lazy loaded
    }

    @Override
    protected void save(YamlConfiguration modelsConf) {
        // no saving. lazy loaded
    }

    private String id(World world, ProtectedRegion pr) {
        return world.getName() + ";" + pr.getId();
    }

}
