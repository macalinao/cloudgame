/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.stats;

import org.bukkit.Location;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import java.util.UUID;

/**
 * Represents the death of a player in a minigame, storing the cause along with
 * timestamp, location, victim and killer
 *
 * @author ian
 */
public class Death {
    /**
     * The player who died
     */
    private final UUID player;
    /**
     * The killer of the player
     */
    private final UUID killer;
    /**
     * The cause of death
     */
    private final DamageCause cause;
    /**
     * When the death occurred
     */
    private final long timestamp;
    /**
     * The location of the death
     */
    private final Location location;

    public Death(UUID player, UUID killer, DamageCause cause, long timestamp, Location location) {
        this.player = player;
        this.killer = killer;
        this.cause = cause;
        this.timestamp = timestamp;
        this.location = location;
    }

    /**
     * Gets the player who died
     *
     * @return The player who died
     */
    public UUID getPlayer() {
        return player;
    }

    /**
     * Gets the player's killer
     *
     * @return The killer involved in the death
     */
    public UUID getKiller() {
        return killer;
    }

    /**
     * Gets the cause of death
     *
     * @return The cause of this Death
     */
    public DamageCause getCause() {
        return cause;
    }

    /**
     * Gets the timestamp for this death
     *
     * @return When this death occurred
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the Location of this Death
     *
     * @return Where this Death occurred
     */
    public Location getLocation() {
        return location;
    }

}
