/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.model.arena;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import pw.ian.albkit.util.Rand;
import pw.ian.cloudgame.model.Model;
import pw.ian.cloudgame.model.region.Region;

/**
 *
 * @author ian
 */
public class Arena extends Model {

    private String name;

    private Region main;

    private Map<Integer, Location> spawns;

    private Map<String, Object> properties;

    Arena(String id, Region main) {
        this(id, id, main, new HashMap<>(), new HashMap<>());
    }

    Arena(String id, String name, Region main, Map<Integer, Location> spawns, Map<String, Object> properties) {
        super(id);
        this.name = name;
        this.main = main;
        this.spawns = spawns;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getMain() {
        return main;
    }

    public void setMain(Region main) {
        this.main = main;
    }

    /**
     * Adds a spawn to this arena.
     *
     * @param l The location of the spawn to add
     * @return The index of the created spawn.
     */
    public int addSpawn(Location l) {
        for (int i = 0;; i++) {
            if (spawns.containsKey(i)) {
                continue;
            }

            spawns.put(i, l);
            return i;
        }
    }

    /**
     * Resets all spawns of this arena.
     */
    public void resetSpawns() {
        spawns.clear();
    }

    public void setSpawn(int index, Location location) {
        spawns.put(index, location);
    }

    public Location getSpawn(int index) {
        return spawns.get(index);
    }

    public Map<Integer, Location> getSpawns() {
        return new HashMap<>(spawns);
    }

    public Location getNextTeamSpawn(int numTeams, int teamId) {
        if (spawns.isEmpty()) {
            return null;
        }
        int spawnCt = spawns.size();
        int spawnsPerTeam = spawnCt / numTeams;
        int spawnId = Rand.r.nextInt(spawnsPerTeam);
        return getSpawn(teamId * spawnsPerTeam + spawnId);
    }

    public Location getNextSpawn() {
        if (spawns.isEmpty()) {
            return null;
        }
        Location spawn = getSpawn(Rand.r.nextInt(spawns.size()));
        return (spawn == null) ? getNextSpawn() : spawn;
    }

    /**
     * Gets a map of all properties of this arena.
     *
     * @return
     */
    public Map<String, Object> getProperties() {
        return new HashMap<>(properties);
    }

    /**
     * Gets a property of this arena.
     *
     * @param name
     * @return
     */
    public Object getProperty(String name) {
        return properties.get(name);
    }

    /**
     * Sets a property of this arena.
     *
     * @param name
     * @param property
     */
    public void setProperty(String name, Object property) {
        properties.put(name, property);
    }

    /**
     * Checks if this arena has the given property.
     *
     * @param name
     * @return
     */
    public boolean hasProperty(String name) {
        return properties.containsKey(name);
    }

    /**
     * Removes a property from the arena.
     *
     * @param property
     */
    public void removeProperty(String property) {
        properties.remove(property);
    }
}
