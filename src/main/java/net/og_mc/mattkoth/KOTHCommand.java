/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.command.PlayerCommandHandler;
import com.simplyian.cloudgame.events.GameJoinEvent;
import com.simplyian.cloudgame.events.GameLeaveEvent;
import com.simplyian.cloudgame.events.GameSpectateEvent;
import com.simplyian.cloudgame.events.GameUnspectateEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.model.arena.Arena;
import com.simplyian.cloudgame.model.region.Region;
import com.simplyian.cloudgame.util.Messaging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * The main command for all that shizzzzle
 *
 * @author ian
 */
public class KOTHCommand extends PlayerCommandHandler {

    private final MattKOTH koth;

    public KOTHCommand(MattKOTH koth) {
        this.koth = koth;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            noArgs(player);
            return;
        }

        switch (args[0].toLowerCase()) {
            case "join":
                join(player, args);
                return;
            case "leave":
                leave(player, args);
                return;
            case "start":
                start(player, args);
                return;
            case "setregion":
                setregion(player, args);
                return;
            case "setspawn":
                setspawn(player, args);
                return;
            case "spectate":
                spectate(player, args);
                return;
        }

        noArgs(player);
    }

    private void noArgs(Player player) {
        // TODO add to help menu based on permissions
        Messaging.sendBanner(player,
                "/koth join - Join the koth",
                "/koth leave - Leaves the koth",
                "/koth start - Starts a KOTH if there isn't already one going on",
                "/koth setregion - Set the koth region",
                "/koth setspawn - Sets a spawn on the koth map");
    }

    private void join(Player player, String[] args) {
        Bukkit.getPluginManager().callEvent(new GameJoinEvent(koth.getGame(), player));
    }

    private void leave(Player player, String[] args) {
        Bukkit.getPluginManager().callEvent(new GameLeaveEvent(koth.getGame(), player));
    }

    private void start(Player player, String[] args) {
        if (args.length <= 1) {
            player.sendMessage(ChatColor.RED + "Usage: /koth start <arena>");
            return;
        }

        Arena arena = koth.getPlugin().getModelManager().getArenas().findById(args[1]);
        if (arena == null) {
            player.sendMessage(ChatColor.RED + "That arena does not exist.");
            return;
        }

        if (koth.getGame() != null) {
            player.sendMessage(ChatColor.RED + "A game has already been started.");
            return;
        }

        Game<KOTHState> game = koth.getPlugin().getGameManager().createGame(koth, arena);
        if (game == null) {
            player.sendMessage(ChatColor.RED + "KOTH is not supported on the given arena.");
            return;
        }

        koth.setGame(game);
        player.sendMessage(ChatColor.GREEN + "KOTH countdown started.");
    }

    private void setregion(Player player, String[] args) {
        if (args.length <= 2) {
            player.sendMessage(ChatColor.RED + "Usage: /koth setregion <region> <arena>");
            return;
        }

        Region region = null;
        if (args[1].contains(";")) {
            region = koth.getPlugin().getModelManager().getRegions().findById(args[1]);
        } else {
            region = koth.getPlugin().getModelManager().getRegions().find(player.getWorld(), args[1]);
        }

        if (region == null) {
            player.sendMessage(ChatColor.RED + "Invalid region.");
            return;
        }

        Arena arena = koth.getPlugin().getModelManager().getArenas().findById(args[2]);
        if (arena == null) {
            Region arenaRegion = null;
            if (args[2].contains(";")) {
                arenaRegion = koth.getPlugin().getModelManager().getRegions().findById(args[2]);
            } else {
                arenaRegion = koth.getPlugin().getModelManager().getRegions().find(player.getWorld(), args[2]);
            }

            if (arenaRegion == null) {
                player.sendMessage(ChatColor.RED + "Invalid arena region.");
                return;
            }

            arena = koth.getPlugin().getModelManager().getArenas().create(arenaRegion);
            player.sendMessage(ChatColor.YELLOW + "The arena did not exist, so one was created on that region.");
        }

        arena.setProperty("koth.hill", region.getId());
        player.sendMessage(ChatColor.GREEN + "The hill of arena " + ChatColor.YELLOW + arena.getId()
                + ChatColor.GREEN + " has been set to " + ChatColor.YELLOW + region.getId() + ChatColor.GREEN + ".");
    }

    private void setspawn(Player player, String[] args) {
        if (args.length <= 2) {
            player.sendMessage(ChatColor.RED + "Usage: /koth setspawn <arena> <spawn number>");
            return;
        }

        Arena arena = koth.getPlugin().getModelManager().getArenas().findById(args[1]);
        if (arena == null) {
            player.sendMessage(ChatColor.RED + "That arena does not exist.");
            return;
        }

        int spawnNumber = 0;
        try {
            spawnNumber = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            player.sendMessage(ChatColor.RED + "That spawn id is an invalid number.");
            return;
        }

        if (spawnNumber < 1 || spawnNumber > 5) {
            player.sendMessage(ChatColor.RED + "You can only set spawns 1 through 5.");
            return;
        }

        arena.setSpawn(spawnNumber - 1, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Spawn " + spawnNumber + " has been set.");
    }

    private void spectate(Player player, String[] args) {
        if (koth.getGame() == null) {
            koth.sendGameMessage(player, "There is currently no game to spectate.");
        }

        if (koth.getGame().getState().hasSpectator(player)) {
            Bukkit.getPluginManager().callEvent(new GameUnspectateEvent(koth.getGame(), player));
        } else {
            Bukkit.getPluginManager().callEvent(new GameSpectateEvent(koth.getGame(), player));
        }
    }
}
