package pw.ian.cloudgame.gameplay.hostedffa;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.gameplay.Winner;

public class HostedFFAWinner<T extends HostedFFAState> implements Winner<T> {

    private final UUID winner;

    public HostedFFAWinner(Player winner) {
        this.winner = winner.getUniqueId();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(winner);
    }

    public UUID getPlayerId() {
        return winner;
    }

    @Override
    public void sendMessage(String message) {
        getPlayer().sendMessage(message);
    }

    /**
     * Awards the prize to the HostedFFAWinner
     *
     * @param type
     */
    public void awardPrize(String type) {
        if (type.equalsIgnoreCase("easy")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ccrates give 2 " + getPlayer().getName() + " 3");
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ccrates give 3 " + getPlayer().getName() + " 3");
        }
    }
}
