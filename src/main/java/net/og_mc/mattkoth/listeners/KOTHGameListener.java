/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.listeners;

import com.simplyian.cloudgame.events.GameEndEvent;
import com.simplyian.cloudgame.events.GameJoinEvent;
import com.simplyian.cloudgame.events.GameLeaveEvent;
import com.simplyian.cloudgame.events.GameQuitEvent;
import com.simplyian.cloudgame.events.GameSpectateEvent;
import com.simplyian.cloudgame.events.GameStartEvent;
import com.simplyian.cloudgame.events.GameUnspectateEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.listeners.GameListener;
import com.simplyian.cloudgame.util.Messaging;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.tasks.KOTHTimer;
import net.og_mc.mattkoth.MattKOTH;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 *
 * @author ian
 */
public class KOTHGameListener extends GameListener<KOTHState> {

    public KOTHGameListener(MattKOTH koth) {
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
            getGameplay().getPlugin().getPlayerStateManager().saveState(p);

            Location spawn = game.getArena().getNextSpawn();
            p.teleport(spawn);
        }

        state.setStarted();
        (new KOTHTimer(game)).runTaskTimer(getGameplay().getPlugin(), 2L, 2L);
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

        if (state.hasPlayer(p)) {
            game.getGameplay().sendGameMessage(p, "You have already joined the KOTH queue!");
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
            if (!state.hasPlayer(p)) {
                game.getGameplay().sendGameMessage(p, "You aren't part of the KOTH queue.");
                return;
            }

            state.removePlayer(p);
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
            game.getState().removePlayer(p);
            getGameplay().getPlugin().getPlayerStateManager().loadState(p);
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
        getGameplay().getPlugin().getPlayerStateManager().loadState(event.getPlayer());
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        Player winner = game.getState().getCapturer();
        if (winner == null) {
            game.broadcast("Game over! Nobody won!");
        } else {
            game.broadcast(ChatColor.YELLOW + winner.getName() + ChatColor.RED + " has won the KOTH!");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ccrates give 3 " + winner.getName() + " 3");
        }

        game.restorePlayers();
        getGameplay().getPlugin().getGameManager().removeGame(game);
        ((MattKOTH) getGameplay()).setGame(null);
    }

    @EventHandler
    public void onGameSpectate(GameSpectateEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();

        if (!game.getState().isStarted()) {
            p.sendMessage(ChatColor.RED + "The game hasn't started yet!");
            return;
        }

        getGameplay().getPlugin().getPlayerStateManager().saveState(p);
        game.getState().addSpectator(p);
        p.teleport(game.getArena().getNextSpawn());

        game.getGameplay().sendGameMessage(p, "Type /koth spectate again to exit the mode!");
    }

    @EventHandler
    public void onGameUnspectate(GameUnspectateEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();

        game.getState().removeSpectator(p);
        getGameplay().getPlugin().getPlayerStateManager().loadState(p);

        game.getGameplay().sendGameMessage(p, "You are no longer spectating the game.");
    }
}
