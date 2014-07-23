/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa.listeners;

import pw.ian.cloudgame.events.GameJoinEvent;
import pw.ian.cloudgame.events.GameLeaveEvent;
import pw.ian.cloudgame.events.GameQuitEvent;
import pw.ian.cloudgame.events.GameSpectateEvent;
import pw.ian.cloudgame.events.GameUnspectateEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAState;
import pw.ian.cloudgame.gameplay.GameListener;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 *
 * @author ian
 */
public class FFAGamePlayerListener extends GameListener<HostedFFAState> {

    private boolean barAPI;

    public FFAGamePlayerListener(HostedFFA koth, boolean barAPI) {
        super(koth);

        this.barAPI = barAPI;
    }

    @EventHandler
    public void onGameJoin(GameJoinEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }

        HostedFFAState state = game.getState();
        Player p = event.getPlayer();

        if (state.isStarted()) {
            game.getGameplay().sendGameMessage(p, "You can't join a " + getGameplay().getId() + " that is already in progress.");
            return;
        }

        if (state.hasPlayer(p)) {
            game.getGameplay().sendGameMessage(p, "You have already joined the " + getGameplay().getId() + " queue!");
            return;
        }

        if (state.getHost() != null && state.getHost().equals(p)) {
            game.getGameplay().sendGameMessage(p, "You can't join the game if you are the host!");
            return;
        }

        state.addPlayer(p);
        getGameplay().sendBanner(p, "You've joined the " + getGameplay().getId() + "! Pay attention to the countdown.",
                "Want to leave the game? Type $D/" + getGameplay().getId() + " leave$L!");
    }

    @EventHandler
    public void onGameLeave(GameLeaveEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }

        HostedFFAState state = game.getState();
        Player p = event.getPlayer();

        if (!state.isStarted()) {
            if (!state.hasPlayer(p)) {
                game.getGameplay().sendGameMessage(p, "You aren't part of the " + getGameplay().getId() + " queue.");
                return;
            }

            state.removePlayer(p);
            game.getGameplay().sendGameMessage(p, "You've left the " + getGameplay().getId() + ". To rejoin, type $H/koth join$M!");
            return;
        }

        // Kills check
        boolean failedKillsCheck = game.getStats().getKillCount(p) == 0;

        // Distance check
        boolean failedDistanceCheck = false;
        for (Player player : state.getPlayers()) {
            if (p.getWorld().equals(player.getWorld()) && p.getLocation().distanceSquared(player.getLocation()) < 20 * 20) {
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
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
            if (barAPI) {
                BarAPI.removeBar(p);
            }
            game.getGameplay().sendGameMessage(p, "You have left the game.");
        }
    }

    @EventHandler
    public void onGameQuit(GameQuitEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        if (game.getState().isProvideArmor()) {
            getGameplay().getPlugin().getPlayerStateManager().queueLoadState(event.getPlayer());
        }
        p.setGameMode(GameMode.SURVIVAL);

        game.getState().removePlayer(p);
        if (barAPI) {
            BarAPI.removeBar(p);
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
    }

    @EventHandler
    public void onGameSpectate(GameSpectateEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        if (!game.getState().isStarted()) {
            p.sendMessage(ChatColor.RED + "The game hasn't started yet!");
            return;
        }

        if (game.getState().hasPlayer(p)) {
            p.sendMessage(ChatColor.RED + "You can't use this command as a player!");
            return;
        }

        getGameplay().getPlugin().getPlayerStateManager().saveState(p);
        game.getState().addSpectator(p);
        for (Player other : Bukkit.getOnlinePlayers()) {
            other.hidePlayer(p);
        }
        p.teleport(game.getArena().getNextSpawn());
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);

        game.getGameplay().sendGameMessage(p, "Type /" + getGameplay().getId() + " spectate again to exit the mode!");
    }

    @EventHandler
    public void onGameUnspectate(GameUnspectateEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();

        game.getState().removeSpectator(p);
        getGameplay().getPlugin().getPlayerStateManager().queueLoadState(p);
        for (Player other : Bukkit.getOnlinePlayers()) {
            other.showPlayer(p);
        }
        p.setFlying(false);
        if (barAPI) {
            BarAPI.removeBar(p);
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());

        game.getGameplay().sendGameMessage(p, "You are no longer spectating the game.");
    }
}
