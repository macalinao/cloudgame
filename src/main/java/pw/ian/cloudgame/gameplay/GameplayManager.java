/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay;

import java.util.HashMap;
import java.util.Map;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.gameplay.core.CoreGameListener;
import pw.ian.cloudgame.gameplay.core.SpectatorListener;

/**
 *
 * @author ian
 */
public class GameplayManager {

    private final CloudGame plugin;

    private final Map<String, Gameplay> gameplays = new HashMap<>();

    public GameplayManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    /**
     * Calls onDisable on all gameplays.
     */
    public void onDisable() {
        for (Gameplay g : gameplays.values()) {
            g.onDisable();
        }
    }

    /**
     * Adds a gameplay to the manager.
     *
     * @param gameplay
     */
    public void addGameplay(Gameplay gameplay) {
        gameplays.put(gameplay.getId(), gameplay);
        plugin.getServer().getPluginManager().registerEvents(new CoreGameListener(gameplay), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SpectatorListener(gameplay), plugin);
        gameplay.onEnable();
    }

    /**
     * Gets a Gameplay from its id.
     *
     * @param id
     * @return
     */
    public Gameplay getGameplay(String id) {
        return gameplays.get(id);
    }

}
