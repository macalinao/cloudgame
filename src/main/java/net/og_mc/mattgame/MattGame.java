package net.og_mc.mattgame;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.util.Random;
import net.og_mc.mattgame.command.Commands;
import net.og_mc.mattgame.model.ModelManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MattGame extends JavaPlugin {

    public static MattGame i;

    private Commands commands;

    private ModelManager modelManager;

    public WorldGuardPlugin wg;

    public Random r = new Random();

    @Override
    public void onEnable() {
        i = this;

        commands = new Commands(this);
        commands.registerDefaultCommands();

        modelManager = new ModelManager(this);
        modelManager.load();
    }

    @Override
    public void onDisable() {
        i = null;

        modelManager.save();

        commands = null;
        modelManager = null;
    }

    public Commands getCommands() {
        return commands;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

}
