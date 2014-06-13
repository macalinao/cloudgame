package com.simplyian.cloudgame;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.simplyian.cloudgame.command.Commands;
import com.simplyian.cloudgame.game.GameManager;
import com.simplyian.cloudgame.gameplay.GameplayManager;
import com.simplyian.cloudgame.inventory.InventoryManager;
import com.simplyian.cloudgame.model.ModelManager;
import com.simplyian.mattkoth.MattKOTH;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudGame extends JavaPlugin {

    public static CloudGame i;

    private Commands commands;

    private ModelManager modelManager;

    private GameManager gameManager;

    private GameplayManager gameplayManager;

    private InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        i = this;

        commands = new Commands(this);
        commands.registerDefaultCommands();

        modelManager = new ModelManager(this);
        modelManager.load();

        gameManager = new GameManager(this);

        gameplayManager = new GameplayManager(this);
        gameplayManager.addGameplay(new MattKOTH(this));
        gameplayManager.onEnable();

        inventoryManager = new InventoryManager(this);
        inventoryManager.load();
    }

    @Override
    public void onDisable() {
        inventoryManager.save();
        gameplayManager.onDisable();
        modelManager.save();

        commands = null;
        modelManager = null;
        gameManager = null;
        gameplayManager = null;
        inventoryManager = null;

        i = null;
    }

    public Commands getCommands() {
        return commands;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public static WorldGuardPlugin wg() {
        return (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    }

}
