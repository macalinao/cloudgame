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
public class ArenaListSpawnsCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaListSpawnsCommand(CloudGame plugin) {
        super("listspawns");
        this.plugin = plugin;
        setUsage("/arena listspawns");
        setDescription("Lists the spawns of an arena.");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Location loc = player.getLocation();
        Arena a = plugin.getModelManager().getArenas().find(loc);
        if (a == null) {
            player.sendMessage(ChatColor.RED + "There is no arena where you are standing.");
            return;
        }

        player.sendMessage(ChatColor.YELLOW + "===== SPAWNS =====");
        for (Entry<Integer, Location> spawn : a.getSpawns().entrySet()) {
            player.sendMessage(ChatColor.GREEN + "#" + spawn.getKey() + ": " + ChatColor.YELLOW + spawn.getValue());
        }
    }

}
