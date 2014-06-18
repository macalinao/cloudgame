/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.states;

import com.simplyian.cloudgame.gameplay.State;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class FFAState implements State {

    private final Set<UUID> players = new HashSet<>();

    public void addPlayer(Player p) {
        players.add(p.getUniqueId());
    }

    public boolean hasPlayer(Player p) {
        return players.contains(p.getUniqueId());
    }

    public void removePlayer(Player p) {
        players.remove(p.getUniqueId());
    }

    /**
     * Gets all online players in the FFA state
     *
     * @return
     */
    public List<Player> getPlayers() {
        List<Player> ret = new ArrayList<>();
        for (UUID u : players) {
            ret.add(Bukkit.getPlayer(u));
        }
        return ret;
    }
}
