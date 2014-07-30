package pw.ian.cloudgame.hosted;

import pw.ian.cloudgame.gameplay.GameMaster;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * A GameMaster implementation where the game master is a player, or 'host'.
 *
 * @author Ollie
 */
public class Host implements GameMaster {

    private final UUID uuid;

    /**
     * C'tor
     *
     * @param uuid The UUID
     */
    public Host(final UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets the unique id of this Host.
     *
     * @return uuid
     */
    public UUID getUniqueId() {
        return uuid;
    }

    /**
     * Gets the player of this Host.
     *
     * @return player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
