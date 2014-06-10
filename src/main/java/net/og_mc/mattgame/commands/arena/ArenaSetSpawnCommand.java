/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.commands.arena;

import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.command.PlayerCommandHandler;
import net.og_mc.mattgame.model.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class ArenaSetSpawnCommand extends PlayerCommandHandler {

    public ArenaSetSpawnCommand(MattGame plugin, String permission) {
        super(plugin, permission);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Location loc = player.getLocation();
        Arena a = plugin.getModelManager().getArenas().find(loc);
        if (a == null) {
            player.sendMessage(ChatColor.RED + "There is no arena where you are standing.");
            return;
        }

        int index = a.addSpawn(loc);
        player.sendMessage(ChatColor.GREEN + "Spawn point #" + index + " added.");
    }

}
