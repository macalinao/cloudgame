/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay;

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
public abstract class FFAState implements State {

    private final Set<UUID> spectators = new HashSet<>();

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

    @Override
    public List<Player> getPlayers() {
        List<Player> ret = new ArrayList<>();
        for (UUID u : players) {
            Player player = Bukkit.getPlayer(u);
            if (player != null) {
                ret.add(player);
            }
        }
        return ret;
    }

    public void addSpectator(Player p) {
        spectators.add(p.getUniqueId());
    }

    public boolean hasSpectator(Player p) {
        return spectators.contains(p.getUniqueId());
    }

    public void removeSpectator(Player p) {
        spectators.remove(p.getUniqueId());
    }

    @Override
    public List<Player> getSpectators() {
        List<Player> ret = new ArrayList<>();
        for (UUID u : spectators) {
            Player player = Bukkit.getPlayer(u);
            if (player != null) {
                ret.add(player);
            }
        }
        return ret;
    }
}
