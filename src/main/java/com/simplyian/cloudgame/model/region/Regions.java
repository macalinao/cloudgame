/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.model.region;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import static com.simplyian.cloudgame.CloudGame.wg;
import com.simplyian.cloudgame.model.ModelManager;
import com.simplyian.cloudgame.model.Models;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author ian
 */
public class Regions extends Models<Region> {

    public Regions(ModelManager modelManager) {
        super(modelManager, "regions");
    }

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

    /**
     * Finds a region from its location.
     *
     * @param loc
     * @return
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
