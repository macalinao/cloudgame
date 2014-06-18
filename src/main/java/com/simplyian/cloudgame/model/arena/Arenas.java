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
import org.bukkit.entity.Player;

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
        if (has(r.getId())) {
            return null;
        }
        add(a);
        return a;
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

    public Arena find(Player player, String name) {
        Region r = getModelManager().getRegions().find(player.getWorld(), name);
        return findByRegion(r);
    }

    /**
     * Finds an arena from its main region.
     *
     * @param r
     * @return
     */
    public Arena findByMain(Region r) {
        if (r == null) {
            return null;
        }
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
        return findByMain(r);
    }

    @Override
    protected void load(YamlConfiguration modelsConf) {
        for (String key : modelsConf.getKeys(false)) {
            ConfigurationSection sect = modelsConf.getConfigurationSection(key);

            String id = sect.getString("id");
            String name = sect.getString("name");
            Region main = getModelManager().getRegions().findById(sect.getString("main"));

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

            Arena a = new Arena(id, name, main, spawns, properties);
            add(a);
        }
    }

    @Override
    protected void save(YamlConfiguration modelsConf) {
        for (Arena a : findAll()) {
            ConfigurationSection sect = modelsConf.createSection(a.getId());

            sect.set("id", a.getId());
            sect.set("name", a.getName());
            sect.set("main", a.getMain().getId());

            ConfigurationSection spawnsSect = sect.createSection("spawns");
            for (Entry<Integer, Location> spawnEntry : a.getSpawns().entrySet()) {
                spawnsSect.set(spawnEntry.getKey().toString(), LocationUtils.serialize(spawnEntry.getValue()));
            }

            sect.set("properties", a.getProperties());
        }
    }

}
