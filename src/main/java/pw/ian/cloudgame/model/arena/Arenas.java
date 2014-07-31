/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.model.arena;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pw.ian.albkit.util.LocationUtils;
import pw.ian.cloudgame.model.ModelManager;
import pw.ian.cloudgame.model.Models;
import pw.ian.cloudgame.model.region.Region;

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
     * Finds an arena.
     *
     * @param fuzzy Search string
     * @return Arena, or null if no matches.
     */
    public Arena find(String fuzzy) {
        Arena bestMatch = findById(fuzzy);
        if (bestMatch != null) {
            return bestMatch;
        }

        if (fuzzy.contains(";")) { // Not found
            return null;
        }

        // We are trusting that they have it spelled right; they just have case wrong if anything.
        for (Arena a : findAll()) {
            if (a.getMain().getId().equals(fuzzy)) {
                return a;
            } else if (a.getMain().getId().equalsIgnoreCase(fuzzy)) {
                bestMatch = a;
            }
        }
        return bestMatch;
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
     * Finds an arena from a player requesting an arena.
     *
     * @param player
     * @param name
     * @return
     */
    public Arena find(Player player, String name) {
        Region r = getModelManager().getRegions().findById(name);
        if (r == null) {
            r = getModelManager().getRegions().find(player.getWorld(), name);
        }
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
                int spawnIndex = -1;
                try {
                    spawnIndex = Integer.parseInt(spawnIndexStr);
                } catch (NumberFormatException ex) {
                    continue;
                }
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
