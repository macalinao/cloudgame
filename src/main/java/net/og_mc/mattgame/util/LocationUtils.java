/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 *
 * @author ian
 */
public final class LocationUtils {

    private LocationUtils() {
    }

    public static Location deserialize(String str) {
        String str2loc[] = str.split("@");
        World w = Bukkit.getWorld(str2loc[0]);
        if (w == null) {
            return null;
        }
        Location loc = new Location(w, 0, 0, 0);
        loc.setX(Integer.parseInt(str2loc[1]));
        loc.setY(Integer.parseInt(str2loc[2]));
        loc.setZ(Integer.parseInt(str2loc[3]));
        loc.setYaw(Float.parseFloat(str2loc[4]));
        loc.setPitch(Float.parseFloat(str2loc[5]));
        return loc;

    }

    public static String serialize(Location loc) {
        return loc.getWorld().getName() + "@"
                + loc.getBlockX() + "@"
                + loc.getBlockY() + "@"
                + loc.getBlockZ() + "@"
                + loc.getYaw() + "@"
                + loc.getPitch();
    }
}
