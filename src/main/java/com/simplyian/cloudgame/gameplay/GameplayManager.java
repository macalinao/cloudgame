/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import java.util.HashMap;
import java.util.Map;
import com.simplyian.cloudgame.CloudGame;

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
     * Calls onEnable on all gameplays.
     */
    public void onEnable() {
        for (Gameplay g : gameplays.values()) {
            g.onEnable();
        }
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
