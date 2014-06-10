/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.commands.room;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class RoomUnsetCommand extends CommandHandler {

    public RoomUnsetCommand(MattGame plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /roomunset <region> [world=current]");
            return;
        }

        String regionName = args[0];

        World world = null;

        if (args.length >= 2) {
            String worldName = args[0];
            world = Bukkit.getWorld(worldName);
        } else if (sender instanceof Player) {
            world = ((Player) sender).getWorld();
        }

        if (world == null) {
            sender.sendMessage(ChatColor.RED + "You need to specify a valid world.");
        }

        ProtectedRegion pr = plugin.wg.getRegionManager(world).getRegionExact(regionName);
        if (pr == null) {
            sender.sendMessage(ChatColor.RED + "The region '" + regionName + "' does not exist in the world '" + world.getName() + "'.");
            return;
        }

        if (plugin.getModelManager().getRooms().find(world, pr) == null) {
            sender.sendMessage(ChatColor.RED + "The given world and region does not correspond to an existing room.");
            return;
        }

        plugin.getModelManager().getRooms().remove(world, pr);
        sender.sendMessage(ChatColor.GREEN + "The room has been unset.");

    }

}
