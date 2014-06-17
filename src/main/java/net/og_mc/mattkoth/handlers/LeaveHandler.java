/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.handlers;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.MessageHandler;
import com.simplyian.cloudgame.util.Messaging;
import static com.sk89q.worldguard.bukkit.WGBukkit.getPlugin;
import java.util.Map;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.KOTHTimer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class LeaveHandler implements MessageHandler<KOTHState> {

    private final CloudGame plugin;

    public LeaveHandler(CloudGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onReceive(Game<KOTHState> game, Map<String, Object> message) {
        KOTHState state = game.getState();
        Player p = (Player) message.get("player");

        if (!state.isStarted()) {
            game.getState().removePlayer(p);
            game.getGameplay().sendGameMessage(p, "You've left the KOTH. To rejoin, type " + ChatColor.YELLOW + "/koth join" + ChatColor.RED + "!");
            return;
        }

        // Kills check
        boolean failedKillsCheck = game.getStats().getKillCount(p) == 0;

        // Distance check
        boolean failedDistanceCheck = false;
        for (Player player : state.getPlayers()) {
            if (p.getLocation().distanceSquared(player.getLocation()) < 20 * 20) {
                failedDistanceCheck = true;
                break;
            }
        }

        if (failedKillsCheck) {
            game.getGameplay().sendGameMessage(p, "You must kill at least one person before leaving!");
        }
        if (failedDistanceCheck) {
            game.getGameplay().sendGameMessage(p, "You must be at least 20 blocks away from another player!");
        }

        if (!failedKillsCheck && !failedDistanceCheck) {
            restorePlayer(game, p);
            game.getGameplay().sendGameMessage(p, "You have left the game.");
        }
    }

    /**
     * Restores the player to how they were before.
     *
     * @param game
     * @param p
     */
    private void restorePlayer(Game<KOTHState> game, Player p) {
        game.getState().removePlayer(p);
        plugin.getInventoryManager().restoreInventory(p);
        // TODO restore old location
    }

}
