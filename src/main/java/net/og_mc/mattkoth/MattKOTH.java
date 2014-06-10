package net.og_mc.mattkoth;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.util.Random;
import org.bukkit.plugin.java.JavaPlugin;

public class MattKOTH extends JavaPlugin {

    public static MattKOTH i;

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
