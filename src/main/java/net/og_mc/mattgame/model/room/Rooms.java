/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model.room;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.model.ModelManager;
import net.og_mc.mattgame.model.Models;
import org.bukkit.Bukkit;
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

}
