/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.command.PlayerCommandHandler;
import com.simplyian.cloudgame.events.GameJoinEvent;
import com.simplyian.cloudgame.events.GameLeaveEvent;
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

        boolean success = koth.createGame(arena);
        if (!success) {
            player.sendMessage(ChatColor.RED + "A game has already been started.");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "KOTH countdown started.");
    }

    private void setregion(Player player, String[] args) {
        if (args.length <= 2) {
            player.sendMessage(ChatColor.RED + "Usage: /koth setregion <region> <arena>");
            return;
        }

        Region region = koth.getPlugin().getModelManager().getRegions().findById(args[1]);
        if (region == null) {
            player.sendMessage(ChatColor.RED + "Invalid region.");
            return;
        }

        Arena arena = koth.getPlugin().getModelManager().getArenas().findById(args[2]);
        if (arena == null) {
            player.sendMessage(ChatColor.RED + "That arena does not exist.");
            return;
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

        arena.setSpawn(spawnNumber, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Spawn " + spawnNumber + " has been set.");
    }
}
