/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.commands.arena;

import java.util.Map.Entry;
import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.command.PlayerCommandHandler;
import com.simplyian.cloudgame.model.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class ArenaInfoCommand extends PlayerCommandHandler {

    public ArenaInfoCommand(CloudGame plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Location loc = player.getLocation();
        Arena a = plugin.getModelManager().getArenas().find(loc);
        if (a == null) {
            player.sendMessage(ChatColor.RED + "There is no arena where you are standing.");
            return;
        }

        player.sendMessage(ChatColor.YELLOW + "========== ARENA INFO ==========");
        player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.YELLOW + a.getId()
                + "    " + ChatColor.GREEN + "Name: " + ChatColor.YELLOW + a.getName());
        player.sendMessage(ChatColor.GREEN + "Lobby: " + ChatColor.YELLOW + a.getLobby().getId()
                + "    " + ChatColor.GREEN + "Main: " + ChatColor.YELLOW + a.getMain().getId());
        player.sendMessage(ChatColor.GREEN + "Spawns: " + ChatColor.YELLOW + "Use /arenalistspawns");
    }

}
