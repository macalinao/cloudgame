package pw.ian.cloudgame.gameplay.hostedffa;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.gameplay.core.PlayerWinner;

public class HostedFFAWinner<T extends HostedFFAState> extends PlayerWinner<T> {
    public HostedFFAWinner(Player winner) {
        super(winner);
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
