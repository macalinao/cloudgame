/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa.listeners;

import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import pw.ian.cloudgame.events.GameJoinEvent;
import pw.ian.cloudgame.events.GameLeaveEvent;
import pw.ian.cloudgame.events.GameQuitEvent;
import pw.ian.cloudgame.events.GameUnspectateEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.FFAParticipants;
import pw.ian.cloudgame.gameplay.GameListener;
import pw.ian.cloudgame.hosted.PlayerHost;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import pw.ian.cloudgame.states.Status;

/**
 *
 * @author ian
 */
public class FFAGamePlayerListener extends GameListener {

    private boolean barAPI;

    public FFAGamePlayerListener(HostedFFA koth) {
        super(koth);

        barAPI = koth.getPlugin().getServer().getPluginManager().isPluginEnabled("BarAPI");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGameJoin(GameJoinEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        Status status = game.state(Status.class);

        if (status.isStarted()) {
            game.getGameplay().sendGameMessage(p, "You can't join a " + getGameplay().getId() + " that is already in progress.");
            event.setCancelled(true);
            return;
        }

        FFAParticipants parts = (FFAParticipants) game.getParticipants();

        if (game.getGameMaster() instanceof PlayerHost && p.getUniqueId().equals(((PlayerHost) game.getGameMaster()).getUniqueId())) {
            game.getGameplay().sendGameMessage(p, "You can't join the game if you are the host!");
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onGameLeave(GameLeaveEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        FFAParticipants state = (FFAParticipants) game.getParticipants();

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

        if (failedKillsCheck || failedDistanceCheck) {
            event.setCancelled(true);
            return;
        }

        if (barAPI) {
            BarAPI.removeBar(p);
        }
    }

    @EventHandler
    public void onGameQuit(GameQuitEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        if (barAPI) {
            BarAPI.removeBar(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onGameUnspectate(GameUnspectateEvent event) {
        if (barAPI) {
            BarAPI.removeBar(event.getPlayer());
        }
    }
}
