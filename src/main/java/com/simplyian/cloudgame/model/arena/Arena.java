/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.model.arena;

import java.util.HashMap;
import com.simplyian.cloudgame.model.region.Region;
import java.util.Map;
import com.simplyian.cloudgame.model.Model;
import com.simplyian.cloudgame.util.Rand;
import org.bukkit.Location;

/**
 *
 * @author ian
 */
public class Arena extends Model {

    private String name;

    private Region lobby;

    private Region main;

    private Location lobbySpawn;

    private Map<Integer, Location> spawns;

    private Map<String, Object> properties;

    Arena(String id, Region main) {
        this(id, id, null, main, null, new HashMap<Integer, Location>(), new HashMap<String, Object>());
    }

    Arena(String id, String name, Region lobby, Region main, Location lobbySpawn, Map<Integer, Location> spawns, Map<String, Object> properties) {
        super(id);
        this.name = name;
        this.lobby = lobby;
        this.main = main;
        this.lobbySpawn = lobbySpawn;
        this.spawns = spawns;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getLobby() {
        return lobby;
    }

    public void setLobby(Region lobby) {
        this.lobby = lobby;
    }

    public Region getMain() {
        return main;
    }

    public void setMain(Region main) {
        this.main = main;
    }

    public Location getLobbySpawn() {
        return lobbySpawn;
    }

    public void setLobbySpawn(Location lobbySpawn) {
        this.lobbySpawn = lobbySpawn;
    }

    /**
     * Adds a spawn to this arena.
     *
     * @param l
     * @return The index of the created spawn.
     */
    public int addSpawn(Location l) {
        for (int i = 1;; i++) {
            if (spawns.containsKey(i)) {
                continue;
            }

            setSpawn(i, l);
            return i;
        }
    }

    /**
     * Sets a spawn.
     *
     * @param index
     * @param l
     */
    public void setSpawn(int index, Location l) {
        assert index > 0;
        spawns.put(index, l);
    }

    /**
     * Resets all spawns of this arena.
     */
    public void resetSpawns() {
        spawns.clear();
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
        return getSpawn(teamId * spawnsPerTeam + spawnId + 1);
    }

    public Location getNextSpawn() {
        if (spawns.isEmpty()) {
            return null;
        }
        return getSpawn(Rand.r.nextInt(spawns.size()) + 1);
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
}
