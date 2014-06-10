/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.commands.arena;

import com.google.common.base.Joiner;
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
public class ArenaSetNameCommand extends PlayerCommandHandler {

    public ArenaSetNameCommand(MattGame plugin) {
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

        if (args.length == 0) {
            player.sendMessage(usage());
            return;
        }

        String name = Joiner.on(' ').join(args);
        a.setName(name);
        player.sendMessage(ChatColor.GREEN + "Arena name set to " + ChatColor.YELLOW + a.getName() + ChatColor.GREEN + ".");
    }

}
