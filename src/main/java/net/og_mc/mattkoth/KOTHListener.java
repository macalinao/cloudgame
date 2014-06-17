/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.events.GameJoinEvent;
import com.simplyian.cloudgame.events.GameLeaveEvent;
import com.simplyian.cloudgame.events.GameQuitEvent;
import com.simplyian.cloudgame.events.GameStartEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.GameListener;
import com.simplyian.cloudgame.util.Messaging;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 *
 * @author ian
 */
public class KOTHListener extends GameListener<KOTHState> {

    public KOTHListener(MattKOTH koth) {
        super(koth);
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        KOTHState state = game.getState();
        for (Player p : state.getPlayers()) {
            getGameplay().getPlugin().getInventoryManager().backupInventory(p);

            Location spawn = game.getArena().getNextSpawn();
            p.teleport(spawn);
        }

        state.setStarted(true);
        (new KOTHTimer(game)).runTaskTimer(getGameplay().getPlugin(), 20L * 10, 20L * 10);
    }

    @EventHandler
    public void onGameJoin(GameJoinEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        KOTHState state = game.getState();
        Player p = event.getPlayer();

        if (state.isStarted()) {
            game.getGameplay().sendGameMessage(p, "You can't join a KOTH that is already in progress.");
            return;
        }

        state.addPlayer(p);
        Messaging.sendBanner(p, "You've joined the KOTH! Pay attention to the countdown.",
                "Want to leave the game? Type " + ChatColor.DARK_GREEN + "/koth leave" + ChatColor.GREEN + "!");
    }

    @EventHandler
    public void onGameLeave(GameLeaveEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        KOTHState state = game.getState();
        Player p = event.getPlayer();

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

    @EventHandler
    public void onGameQuit(GameQuitEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }
        game.getState().removePlayer(event.getPlayer());
    }

    /**
     * Restores the player to how they were before.
     *
     * @param game
     * @param p
     */
    private void restorePlayer(Game<KOTHState> game, Player p) {
        game.getState().removePlayer(p);
        getGameplay().getPlugin().getInventoryManager().restoreInventory(p);
        // TODO restore old location
    }
}
