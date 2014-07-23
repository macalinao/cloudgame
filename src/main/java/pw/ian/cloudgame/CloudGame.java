package pw.ian.cloudgame;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import pw.ian.cloudgame.command.Commands;
import pw.ian.cloudgame.game.GameManager;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.GameplayManager;
import pw.ian.cloudgame.playerstate.PlayerStateManager;
import pw.ian.cloudgame.model.ModelManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pw.ian.cloudgame.commands.arena.ArenaCommand;

public class CloudGame extends JavaPlugin {

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
        i = this;

        Commands.registerCommand(this, new ArenaCommand(this));

        modelManager = new ModelManager(this);
        modelManager.load();

        gameplayManager = new GameplayManager(this);
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

    public PlayerStateManager getPlayerStateManager() {
        return playerStateManager;
    }

    public void addGameplay(Gameplay<?> gameplay) {
        gameplayManager.addGameplay(gameplay);

        if (gameplayManager != null && gameplayManager.isEnabled()) {
            gameplayManager.enableAdditionalGameplays();
        }
    }

    public static WorldGuardPlugin wg() {
        return (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    }

}
