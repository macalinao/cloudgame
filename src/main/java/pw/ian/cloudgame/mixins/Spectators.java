package pw.ian.cloudgame.mixins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pw.ian.cloudgame.events.GameSpectateEvent;
import pw.ian.cloudgame.events.GameUnspectateEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Mixin;
import pw.ian.cloudgame.states.Status;

/**
 * Basic spectator behavior. Also handles the spectator spectate/unspectate
 * events.
 */
public class Spectators extends Mixin {

    public Spectators(Gameplay gameplay) {
        super(gameplay);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGameSpectate(GameSpectateEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        if (!game.state(Status.class).isStarted()) {
            p.sendMessage(ChatColor.RED + "The game hasn't started yet!");
            event.setCancelled(true);
            return;
        }

        if (game.getParticipants().hasPlayer(p)) {
            p.sendMessage(ChatColor.RED + "You can't use this command as a player!");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameSpectate(GameSpectateEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        getGameplay().getPlugin().getPlayerStateManager().saveState(p);
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

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameUnspectate(GameUnspectateEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        getGameplay().getPlugin().getPlayerStateManager().queueLoadState(p);
        for (Player other : Bukkit.getOnlinePlayers()) {
            other.showPlayer(p);
        }
        p.setFlying(false);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());

        game.getGameplay().sendGameMessage(p, "You are no longer spectating the game.");
    }

    @EventHandler
    public void onSpectatorMoveOutOfArena(PlayerMoveEvent e) {
        if (e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            return;
        }

        Game game = gameSpectated(e.getPlayer());
        if (game == null) {
            return;
        }

        if (game.getArena().getMain().contains(e.getTo())) {
            return;
        }

        game.events().unspectate(e.getPlayer());
    }

    @EventHandler
    public void onSpectatorPickupItem(PlayerPickupItemEvent e) {
        Game game = gameSpectated(e.getPlayer());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onSpectatorDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        Game game = gameSpectated((Player) e.getDamager());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onSpectatorDamaged(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Game game = gameSpectated((Player) e.getEntity());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onSpectatorPotionSplash(PotionSplashEvent e) {
        for (LivingEntity ent : e.getAffectedEntities()) {
            if (!(ent instanceof Player)) {
                continue;
            }

            Game game = gameSpectated((Player) ent);
            if (game == null) {
                continue;
            }

            e.setIntensity(ent, 0);
        }
    }

    @EventHandler
    public void onSpectatorInteract(PlayerInteractEvent e) {
        Game game = gameSpectated(e.getPlayer());
        if (game == null) {
            return;
        }

        e.setCancelled(true);
    }
}
