/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.gameplay;

import java.util.HashMap;
import java.util.Map;
import net.og_mc.mattgame.MattGame;

/**
 *
 * @author ian
 */
public class GameplayManager {

    private final MattGame plugin;

    private final Map<String, Gameplay> gameplays = new HashMap<>();

    public GameplayManager(MattGame plugin) {
        this.plugin = plugin;
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
