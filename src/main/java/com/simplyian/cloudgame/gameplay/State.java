/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import java.util.List;
import org.bukkit.entity.Player;

/**
 * Contains all information about the state of the game. Should be implemented
 * by a game.
 */
public interface State {

    /**
     * Gets a list of all players in this game.
     *
     * @return
     */
    public List<Player> getPlayers();
}
