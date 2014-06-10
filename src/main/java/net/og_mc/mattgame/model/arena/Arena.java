/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.model.arena;

import java.util.HashMap;
import net.og_mc.mattgame.model.room.Room;
import java.util.Map;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.model.Model;
import org.bukkit.Location;

/**
 *
 * @author ian
 */
public class Arena extends Model {

    private String name;

    private Room lobby;

    private Room main;

    private Location lobbySpawn;

    private Map<Integer, Location> spawns;

    Arena(String id, Room main) {
        this(id, id, null, main, null, new HashMap<Integer, Location>());
    }

    Arena(String id, String name, Room lobby, Room main, Location lobbySpawn, Map<Integer, Location> spawns) {
        super(id);
        this.name = name;
        this.lobby = lobby;
        this.main = main;
        this.lobbySpawn = lobbySpawn;
        this.spawns = spawns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getLobby() {
        return lobby;
    }

    public Room getMain() {
        return main;
    }

    public Location getLobbySpawn() {
        return lobbySpawn;
    }

    /**
     * Adds a spawn to this arena.
     *
     * @param l
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

    public Location getSpawn(int index) {
        return spawns.get(index);
    }

    public Map<Integer, Location> getSpawns() {
        return new HashMap<>(spawns);
    }

    public Location getNextTeamSpawn(int numTeams, int teamId) {
        int spawnCt = spawns.size();
        int spawnsPerTeam = spawnCt / numTeams;
        int spawnId = MattGame.i.r.nextInt(spawnsPerTeam);
        return getSpawn(teamId * spawnsPerTeam + spawnId);
    }

    public Location getNextSpawn() {
        return getSpawn(MattGame.i.r.nextInt(spawns.size()));
    }
}
