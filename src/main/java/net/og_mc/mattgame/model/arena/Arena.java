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
import org.bukkit.Location;

/**
 *
 * @author ian
 */
public class Arena {

    private final String id;

    private final Room lobby;

    private final Room main;

    private final Location lobbySpawn;

    private final Map<Integer, Location> spawns;

    Arena(String id, Room lobby, Room main, Location lobbySpawn, Map<Integer, Location> spawns) {
        this.id = id;
        this.lobby = lobby;
        this.main = main;
        this.lobbySpawn = lobbySpawn;
        this.spawns = spawns;
    }

    public String getId() {
        return id;
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
