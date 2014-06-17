/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import com.simplyian.cloudgame.game.Game;
import java.util.Map;

/**
 * Handles messages to games.
 *
 * @author ian
 * @param <T>
 */
public interface MessageHandler<T extends State> {

    /**
     * Called when a message is received by this MessageHandler.
     *
     * @param game
     * @param message
     */
    public void onReceive(Game<T> game, Map<String, Object> message);
}
