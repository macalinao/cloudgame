/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.model.arena;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.simplyian.cloudgame.model.ModelManager;
import com.simplyian.cloudgame.model.Models;
import com.simplyian.cloudgame.model.region.Region;
import com.simplyian.cloudgame.util.LocationUtils;
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

    public Arena create(Region r) {
        Arena a = new Arena(r.getId(), r);
        return add(a);
    }

    /**
     * Finds an arena from a given location.
     *
     * @param location
     * @return
     */
    public Arena find(Location location) {
        Region r = getModelManager().getRegions().find(location);
        return findByRegion(r);
    }

    /**
     * Finds an arena from its lobby.
     *
     * @param r
     * @return
     */
    public Arena findByLobby(Region r) {
        for (Arena a : findAll()) {
            if (a.getLobby().equals(r)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Finds an arena from its main region.
     *
     * @param r
     * @return
     */
    public Arena findByMain(Region r) {
        for (Arena a : findAll()) {
            if (a.getMain().equals(r)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Finds an arena by its region.
     *
     * @param r
     * @return
     */
    public Arena findByRegion(Region r) {
        Arena a = findByLobby(r);
        if (a != null) {
            return a;
        }
        return findByMain(r);
    }

    @Override
    protected void load(YamlConfiguration modelsConf) {
        for (String key : modelsConf.getKeys(false)) {
            ConfigurationSection sect = modelsConf.getConfigurationSection(key);

            String id = sect.getString("id");
            String name = sect.getString("name");
            Region lobby = getModelManager().getRegions().findById(sect.getString("lobby"));
            Region main = getModelManager().getRegions().findById(sect.getString("main"));
            Location lobbySpawn = LocationUtils.deserialize(sect.getString("lobby-spawn"));

            Map<Integer, Location> spawns = new HashMap<>();
            ConfigurationSection spawnsSect = sect.getConfigurationSection("spawns");
            for (String spawnIndexStr : spawnsSect.getKeys(false)) {
                int spawnIndex = Integer.getInteger(spawnIndexStr);
                Location loc = LocationUtils.deserialize(spawnsSect.getString(spawnIndexStr));
                spawns.put(spawnIndex, loc);
            }

            ConfigurationSection propertiesSection = sect.getConfigurationSection("properties");
            Map<String, Object> properties = null;
            if (propertiesSection != null) {
                properties = propertiesSection.getValues(false);
            } else {
                properties = new HashMap<>();
            }

            Arena a = new Arena(id, name, lobby, main, lobbySpawn, spawns, properties);
            add(a);
        }
    }

    @Override
    protected void save(YamlConfiguration modelsConf) {
        for (Arena a : findAll()) {
            ConfigurationSection sect = modelsConf.createSection(a.getId());

            sect.set("id", a.getId());
            sect.set("name", a.getName());
            sect.set("lobby", a.getLobby().getId());
            sect.set("main", a.getMain().getId());
            sect.set("lobby-spawn", LocationUtils.serialize(a.getLobbySpawn()));

            ConfigurationSection spawnsSect = sect.createSection("spawns");
            for (Entry<Integer, Location> spawnEntry : a.getSpawns().entrySet()) {
                spawnsSect.set(spawnEntry.getKey().toString(), spawnEntry.getValue());
            }

            sect.set("properties", a.getProperties());
        }
    }

}
