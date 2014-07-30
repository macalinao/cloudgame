package pw.ian.cloudgame.gameplay.core;

import pw.ian.cloudgame.gameplay.GameMaster;

import java.util.UUID;

/**
 * A GameMaster implementation where the game master is a player, or 'host'
 *
 * @author Ollie
 */
public class HostGameMaster implements GameMaster {
    private UUID host;

    public HostGameMaster() {
    }

    public HostGameMaster(final UUID host) {
        this.host = host;
    }

    public UUID getHost() {
        return host;
    }

    public void setHost(final UUID host) {
        this.host = host;
    }
}
