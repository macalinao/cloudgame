package net.og_mc.mattgame;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.util.Random;
import org.bukkit.plugin.java.JavaPlugin;

public class MattGame extends JavaPlugin {

    public static MattGame i;

    public WorldGuardPlugin wg;

    public Random r = new Random();

    @Override
    public void onEnable() {
        i = this;
    }

    @Override
    public void onDisable() {
        i = null;
    }

}
