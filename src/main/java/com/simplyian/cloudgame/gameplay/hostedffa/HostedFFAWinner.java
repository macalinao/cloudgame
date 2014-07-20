package com.simplyian.cloudgame.gameplay.hostedffa;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Winner;

public class HostedFFAWinner extends Winner<HostedFFAState> {
	private final UUID winner;

	public HostedFFAWinner(Game<HostedFFAState> game, UUID winner) {
		super(game);
		this.winner = winner;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(winner);
	}

	@Override
	public void sendMessage(String message) {
	}

	@Override
	public String getWinnersString() {
		return null;
	}

	@Override
	public boolean includes(UUID player) {
		return player.equals(winner);
	}

	@Override
	public void givePrize(String type) {
		if (type.equalsIgnoreCase("easy")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ccrates give 2 " + getPlayer().getName() + " 3");
		} else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ccrates give 3 " + getPlayer().getName() + " 3");
		}
	}
}
