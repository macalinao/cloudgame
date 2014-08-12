/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pw.ian.cloudgame.events.GameJoinEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Participants;
import pw.ian.cloudgame.gameplay.Mixin;
import pw.ian.cloudgame.states.Status;
import pw.ian.cloudgame.stats.Death;

/**
 *
 * @author ian
 */
public class Core extends Mixin {

    public Core(Gameplay gameplay) {
        super(gameplay);
    }

    @EventHandler
    public void onPlayerMoveOutOfArena(PlayerMoveEvent e) {
        if (e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            return;
        }

        Game game = game(e.getPlayer());
        if (game == null) {
            return;
        }

        if (!game.state(Status.class).isStarted()) {
            return;
        }

        if (game.getArena().getMain().contains(e.getTo())) {
            return;
        }

        game.events().quit(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGameJoin(GameJoinEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player player = event.getPlayer();
        Participants participants = game.getParticipants();
        if (participants.hasPlayer(player)) {
            game.getGameplay().sendGameMessage(player, "You have already joined the " + getGameplay().getId() + " queue!");
            event.setCancelled(true);
            return;
        }

        participants.addPlayer(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location location = p.getLocation();
        Game game = game(location); // use location in case of being queued
        if (game == null) {
            return;
        }

        UUID player = p.getUniqueId();

        UUID killer = null;
        Player killerP = p.getKiller();
        if (killerP != null) {
            killer = killerP.getUniqueId();
        }

        EntityDamageEvent.DamageCause cause = null;
        EntityDamageEvent lastDamageCause = e.getEntity().getLastDamageCause();
        if (lastDamageCause != null) {
            cause = lastDamageCause.getCause();
        }

        long timestamp = System.currentTimeMillis();

        Death death = new Death(player, killer, cause, timestamp, location);
        game.getStats().addDeath(death);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Game g = game(e.getPlayer());
        if (g != null) {
            g.events().quit(e.getPlayer());
        }
    }
}
