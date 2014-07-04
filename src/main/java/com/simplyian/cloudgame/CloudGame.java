package com.simplyian.cloudgame;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.simplyian.cloudgame.command.Commands;
import com.simplyian.cloudgame.game.GameManager;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.GameplayManager;
import com.simplyian.cloudgame.playerstate.PlayerStateManager;
import com.simplyian.cloudgame.model.ModelManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CloudGame extends JavaPlugin {

    private static CloudGame i;

    public static CloudGame inst() {
        return i;
    }

    private Commands commands;

    private ModelManager modelManager;

    private GameManager gameManager;

    private GameplayManager gameplayManager;

    private PlayerStateManager playerStateManager;

    @Override
    public void onEnable() {
        i = this;

        commands = new Commands(this);
        commands.registerDefaultCommands();

        modelManager = new ModelManager(this);
        modelManager.load();

        gameplayManager = new GameplayManager(this);
        addGameplays();
        gameplayManager.onEnable();

        playerStateManager = new PlayerStateManager(this);
        playerStateManager.setupStateRestoreQueueProcessTask();

        gameManager = new GameManager(this);
        getServer().getPluginManager().registerEvents(new CoreListener(this), this);
    }

    @Override
    public void onDisable() {
        gameManager.stopAll();
        gameplayManager.onDisable();
        modelManager.save();

        commands = null;
        modelManager = null;
        gameManager = null;
        gameplayManager = null;
        playerStateManager = null;

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

    public PlayerStateManager getPlayerStateManager() {
        return playerStateManager;
    }

    public abstract void addGameplays();

    protected void addGameplay(Gameplay gameplay) {
        gameplayManager.addGameplay(gameplay);
    }

    public static WorldGuardPlugin wg() {
        return (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    }

}
