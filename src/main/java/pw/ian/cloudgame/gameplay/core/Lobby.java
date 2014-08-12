package pw.ian.cloudgame.gameplay.core;

import pw.ian.cloudgame.gameplay.GameMaster;

import org.bukkit.Location;

/**
 * A GameMaster implementation where the game master is a lobby
 */
public class Lobby implements GameMaster {
    private Location lobbySpawn;

    public Location getLobbySpawn() {
        return lobbySpawn;
    }

    public void setLobbySpawn(final Location lobbySpawn) {
        this.lobbySpawn = lobbySpawn;
    }
}
