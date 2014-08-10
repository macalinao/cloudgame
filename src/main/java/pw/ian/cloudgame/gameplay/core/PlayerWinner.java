package pw.ian.cloudgame.gameplay.core;

import pw.ian.cloudgame.gameplay.State;
import pw.ian.cloudgame.gameplay.Winner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * A player winner of a game
 *
 * @author Ollie
 */
public class PlayerWinner<T extends State> implements Winner<T> {
    /**
     * The unique identifier of the player who is the winner
     */
    private final UUID winner;

    public PlayerWinner(UUID winner) {
        this.winner = winner;
    }

    public PlayerWinner(Player player) {
        this(player.getUniqueId());
    }

    public UUID getPlayerId() {
        return winner;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(winner);
    }

    @Override
    public void sendMessage(String message) {
        getPlayer().sendMessage(message);
    }
}
