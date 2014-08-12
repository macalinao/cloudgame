package pw.ian.cloudgame;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;

import pw.ian.albkit.AlbPlugin;
import pw.ian.albkit.command.Commands;
import pw.ian.cloudgame.builtin.FFA;
import pw.ian.cloudgame.commands.arena.ArenaCommand;
import pw.ian.cloudgame.commands.game.GameCommand;
import pw.ian.cloudgame.commands.gameplay.GameplayCommand;
import pw.ian.cloudgame.game.GameManager;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.GameplayManager;
import pw.ian.cloudgame.model.ModelManager;
import pw.ian.cloudgame.playerstate.PlayerStateManager;

public class CloudGame extends AlbPlugin {

    private static CloudGame i;

    public static CloudGame inst() {
        return i;
    }

    private ModelManager modelManager;

    private GameManager gameManager;

    private GameplayManager gameplayManager;

    private PlayerStateManager playerStateManager;

    @Override
    public void onEnable() {
        init();
        i = this;

        saveDefaultConfig();

        if (getConfig().getBoolean("register-commands", true)) {
            Commands.registerCommand(this, new ArenaCommand(this));
            Commands.registerCommand(this, new GameCommand());
            Commands.registerCommand(this, new GameplayCommand());
        }

        modelManager = new ModelManager(this);
        modelManager.load();

        gameplayManager = new GameplayManager(this);
        gameplayManager.addGameplay(new FFA());

        playerStateManager = new PlayerStateManager(this);
        playerStateManager.setupStateRestoreQueueProcessTask();

        gameManager = new GameManager(this);
        pluginMgr.registerEvents(new CoreListener(this), this);
    }

    @Override
    public void onDisable() {
        gameManager.stopAll();
        gameplayManager.onDisable();
        modelManager.save();

        modelManager = null;
        gameManager = null;
        gameplayManager = null;
        playerStateManager = null;

        i = null;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public GameplayManager getGameplayManager() {
        return gameplayManager;
    }

    public PlayerStateManager getPlayerStateManager() {
        return playerStateManager;
    }

    public void addGameplay(Gameplay gameplay) {
        gameplayManager.addGameplay(gameplay);
    }

    public static WorldGuardPlugin wg() {
        return (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    }

}
