/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.listeners;

import com.simplyian.cloudgame.events.GameEndEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.listeners.GameListener;
import com.simplyian.cloudgame.model.region.Region;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.MattKOTH;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author ian
 */
public class KOTHCaptureListener extends GameListener<KOTHState> {

    private static final ItemStack CAPTURER_HELMET = new ItemStack(Material.WOOL, 1, (byte) 0xe); // Red wool

    public KOTHCaptureListener(MattKOTH koth) {
        super(koth);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInitialCapture(PlayerMoveEvent e) {
        Game<KOTHState> game = game(e.getPlayer());
        if (game == null || !game.getState().isStarted()
                || (game.getState().getCapturer() != null)) {
            return;
        }

        if (e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            return;
        }

        Region hill = getGameplay().getPlugin().getModelManager().getRegions().findById(game.getArena().getProperty("koth-hill").toString());
        if (!hill.contains(e.getTo())) {
            return;
        }

        // Initial capture!
        setCapturer(game, e.getPlayer());
    }

    @EventHandler
    public void onUsurp(PlayerDeathEvent e) {
        Game<KOTHState> game = game(e.getEntity());
        if (game == null || !game.getState().isStarted()
                || (game.getState().getCapturer() == null)
                || !game.getState().getCapturer().equals(e.getEntity())) {
            return;
        }

        Player usurper = e.getEntity().getKiller();
        if (usurper == null) {
            setCapturer(game, null);
            return;
        }

        setCapturer(game, usurper);
    }

    @EventHandler
    public void onCapturerKnockedOut(PlayerMoveEvent e) {
        Game<KOTHState> game = game(e.getPlayer());
        if (game == null || !game.getState().isStarted()
                || (game.getState().getCapturer() == null)
                || !game.getState().getCapturer().equals(e.getPlayer())) {
            return;
        }

        if (e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            return;
        }

        Region hill = getGameplay().getPlugin().getModelManager().getRegions().findById(game.getArena().getProperty("koth-hill").toString());
        if (hill.contains(e.getTo())) {
            return;
        }

        EntityDamageEvent lastDamageCause = e.getPlayer().getLastDamageCause();
        if (lastDamageCause == null || !(lastDamageCause instanceof EntityDamageByEntityEvent)) {
            setCapturer(game, null);
            return;
        }

        EntityDamageByEntityEvent edbe = (EntityDamageByEntityEvent) lastDamageCause;
        if (!(edbe.getDamager() instanceof Player)) {
            setCapturer(game, null);
            return;
        }

        setCapturer(game, (Player) edbe.getDamager());
    }

    private void setCapturer(Game<KOTHState> game, Player player) {
        Player old = game.getState().getCapturer();
        if (old != null) {
            old.getInventory().setHelmet(null);
        }

        if (game.getState().remainingTime() < 0) {
            game.getState().setCapturer(null);
            Bukkit.getPluginManager().callEvent(new GameEndEvent(game));
            return;
        }

        if (player != null) {
            player.getInventory().setHelmet(CAPTURER_HELMET);
            game.getState().setCapturer(player);
            game.broadcast(ChatColor.RED + player.getName() + " is now holding the koth point! "
                    + "Kill them or knock them out of the ring within two minutes or they'll claim their prize!");
        } else {
            game.getState().setCapturer(null);
            // new capturer will be determined by a random moving player
        }
    }
}
