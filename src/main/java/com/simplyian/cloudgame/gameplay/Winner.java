package com.simplyian.cloudgame.gameplay;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.simplyian.cloudgame.game.Game;

public class Winner<T extends State> {

    private final Game<T> game;

    private final List<UUID> winners;

    public Winner(Game<T> game, List<UUID> winners) {
        this.game = game;
        this.winners = winners;
    }

    public Game<T> getGame() {
        return game;
    }

    public List<UUID> getWinners() {
        return winners;
    }
    
    public String winnersString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < winners.size(); i++) {
            UUID uuid = winners.get(i);
            Player player = game.getGameplay().getPlugin().getServer().getPlayer(uuid);
            builder.append(player.getName()).append(i == winners.size() - 1 ? "" : (i == winners.size() - 2 ? " and " : ", "));
        }
        return builder.toString();
    }

}
