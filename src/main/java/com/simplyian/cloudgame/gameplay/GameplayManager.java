/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.simplyian.cloudgame.CloudGame;

/**
 *
 * @author ian
 */
public class GameplayManager {

    private final CloudGame plugin;

    private final Map<String, Gameplay> gameplays = new HashMap<String, Gameplay>();

    private final List<Gameplay> additionalGameplays = new ArrayList<Gameplay>();

    private boolean enabled;

    public GameplayManager(CloudGame plugin) {
        this.plugin = plugin;
    }

    /**
     * Calls onEnable on all gameplays.
     */
    public void onEnable() {
        for (Gameplay g : gameplays.values()) {
            // Add core listener to game
            plugin.getServer().getPluginManager().registerEvents(new CoreGameListener(g), plugin);
            plugin.getServer().getPluginManager().registerEvents(new SpectatorListener(g), plugin);

            g.onEnable();
        }

        enabled = true;
    }

    /**
     * Calls onDisable on all gameplays.
     */
    public void onDisable() {
        for (Gameplay g : gameplays.values()) {
            g.onDisable();
        }
    }

    public void enableAdditionalGameplays() {
        for (final Gameplay gameplay : additionalGameplays) {
            gameplays.put(gameplay.getId(), gameplay);

            plugin.getServer().getPluginManager().registerEvents(new CoreGameListener(gameplay), plugin);
            plugin.getServer().getPluginManager().registerEvents(new SpectatorListener(gameplay), plugin);

            gameplay.onEnable();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Adds a gameplay to the manager.
     *
     * @param gameplay
     */
    public void addGameplay(Gameplay gameplay) {
        if (enabled) {
            gameplays.put(gameplay.getId(), gameplay);
        } else {
            additionalGameplays.add(gameplay);
        }
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
