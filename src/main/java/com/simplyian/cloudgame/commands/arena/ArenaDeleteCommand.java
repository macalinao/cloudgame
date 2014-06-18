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
public class ArenaDeleteCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaDeleteCommand(CloudGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "You need to specify an arena name.");
            return;
        }

        plugin.getModelManager().getArenas().remove(args[0]);
        player.sendMessage(ChatColor.YELLOW + "Arena deleted, if it ever existed.");
    }

}
