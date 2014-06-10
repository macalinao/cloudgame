/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model.arena;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.og_mc.mattgame.model.ModelManager;
import net.og_mc.mattgame.model.Models;
import net.og_mc.mattgame.model.room.Room;
import net.og_mc.mattgame.util.LocationUtils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author ian
 */
public class Arenas extends Models<Arena> {

    public Arenas(ModelManager modelManager) {
        super(modelManager, "arenas");
    }

    @Override
    protected void load(YamlConfiguration modelsConf) {
        for (String key : modelsConf.getKeys(false)) {
            ConfigurationSection sect = modelsConf.getConfigurationSection(key);

            String id = sect.getString("id");
            Room lobby = getModelManager().getRooms().findById(sect.getString("lobby"));
            Room main = getModelManager().getRooms().findById(sect.getString("main"));
            Location lobbySpawn = LocationUtils.deserialize(sect.getString("lobby-spawn"));

            Map<Integer, Location> spawns = new HashMap<>();
            ConfigurationSection spawnsSect = sect.getConfigurationSection("spawns");
            for (String spawnIndexStr : spawnsSect.getKeys(false)) {
                int spawnIndex = Integer.getInteger(spawnIndexStr);
                Location loc = LocationUtils.deserialize(spawnsSect.getString(spawnIndexStr));
                spawns.put(spawnIndex, loc);
            }

            Arena a = new Arena(id, lobby, main, lobbySpawn, spawns);
            add(id, a);
        }
    }

    @Override
    protected void save(YamlConfiguration modelsConf) {
        for (Arena a : findAll()) {
            ConfigurationSection sect = modelsConf.createSection(a.getId());

            sect.set("id", a.getId());
            sect.set("lobby", a.getLobby().getId());
            sect.set("main", a.getMain().getId());
            sect.set("lobby-spawn", LocationUtils.serialize(a.getLobbySpawn()));

            ConfigurationSection spawnsSect = sect.createSection("spawns");
            for (Entry<Integer, Location> spawnEntry : a.getSpawns().entrySet()) {
                spawnsSect.set(spawnEntry.getKey().toString(), spawnEntry.getValue());
            }
        }
    }

}
