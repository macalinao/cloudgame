/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model.room;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.ArrayList;
import java.util.List;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.model.ModelManager;
import net.og_mc.mattgame.model.Models;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author ian
 */
public class Rooms extends Models<Room> {

    public Rooms(ModelManager modelManager) {
        super(modelManager, "rooms");
    }

    /**
     * Override to prevent adding multiple rooms
     *
     * @param id
     * @param model
     * @return
     */
    @Override
    public boolean add(String id, Room model) {
        if (findById(id) != null) {
            return false;
        }
        return super.add(id, model);
    }

    public boolean add(World world, ProtectedRegion pr) {
        String id = id(world, pr);
        return add(id, new Room(id, world, pr));
    }

    public Room find(World world, ProtectedRegion pr) {
        return findById(id(world, pr));
    }

    /**
     * Finds a room from its location.
     *
     * @param loc
     * @return
     */
    public Room find(Location loc) {
        ApplicableRegionSet regions = MattGame.i.wg.getRegionManager(loc.getWorld()).getApplicableRegions(loc);

        for (ProtectedRegion pr : regions) {
            Room r = find(loc.getWorld(), pr);
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
        List<String> roomIds = modelsConf.getStringList("rooms");
        if (roomIds == null || roomIds.isEmpty()) {
            return;
        }
        for (String roomId : roomIds) {
            String[] split = roomId.split(";");
            World world = Bukkit.getWorld(split[0]);
            ProtectedRegion region = MattGame.i.wg.getRegionManager(world).getRegion(split[1]);
            Room r = new Room(roomId, world, region);
            add(roomId, r);
        }
    }

    @Override
    protected void save(YamlConfiguration modelsConf) {
        List<String> ids = new ArrayList<>();
        for (Room r : findAll()) {
            ids.add(r.getId());
        }
        modelsConf.set("rooms", ids);
    }

    private String id(World world, ProtectedRegion pr) {
        return world.getName() + ";" + pr.getId();
    }

}
