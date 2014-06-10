/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.gameplay;

import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.game.Game;

/**
 * Contains all game gameplay.
 *
 * @author ian
 * @param <T> The type of state associated with this game.
 */
public abstract class Gameplay<T extends State> {

    private final MattGame plugin;

    private final String id;

    protected Gameplay(MattGame plugin, String id) {
        this.plugin = plugin;
        this.id = id;
    }

    public MattGame getPlugin() {
        return plugin;
    }

    public String getId() {
        return id;
    }

    /**
     * Called when the plugin is enabled. Set up listeners etc here.
     */
    public void onEnable() {
    }

    /**
     * Called when the plugin is disabled.
     */
    public void onDisable() {
    }

    /**
     * Sets up the gameplay.
     *
     * @param g
     */
    public abstract void setup(Game<T> g);

    /**
     * Creates a new state associated with this game gameplay.
     *
     * @return
     */
    public abstract T newState();

}
