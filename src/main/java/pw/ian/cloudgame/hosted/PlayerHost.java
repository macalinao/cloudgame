package pw.ian.cloudgame.hosted;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * A GameMaster implementation where the game master is a player, or 'host'.
 *
 * @author Ollie
 * @see pw.ian.cloudgame.hosted.Host
 */
public class PlayerHost implements Host {

    private final UUID uuid;

    /**
     * C'tor
     *
     * @param uuid The UUID
     */
    public PlayerHost(final UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * C'tor
     *
     * @param player The player
     */
    public PlayerHost(Player player) {
        this(player.getUniqueId());
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
