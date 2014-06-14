/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.stats;

import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 * Kill information.
 *
 * @author ian
 */
public class Death {

    private final UUID player;

    private final UUID killer;

    private final DamageCause cause;

    private final long timestamp;

    private final Location location;

    public Death(UUID player, UUID killer, DamageCause cause, long timestamp, Location location) {
        this.player = player;
        this.killer = killer;
        this.cause = cause;
        this.timestamp = timestamp;
        this.location = location;
    }

    public UUID getPlayer() {
        return player;
    }

    public UUID getKiller() {
        return killer;
    }

    public DamageCause getCause() {
        return cause;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

}
