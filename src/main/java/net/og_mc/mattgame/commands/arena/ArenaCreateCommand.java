/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.commands.arena;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.command.CommandHandler;
import net.og_mc.mattgame.model.arena.Arena;
import net.og_mc.mattgame.model.arena.Arenas;
import net.og_mc.mattgame.model.room.Room;
import net.og_mc.mattgame.model.room.Rooms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class ArenaCreateCommand extends CommandHandler {

    public ArenaCreateCommand(MattGame plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(usage());
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

        Rooms rooms = plugin.getModelManager().getRooms();
        Room main = rooms.find(world, pr);
        if (main != null) {
            if (plugin.getModelManager().getArenas().findByRoom(main) != null) {
                sender.sendMessage(ChatColor.RED + "That room is already associated with an arena. Try using a different region.");
                return;
            }
        } else {
            rooms.create(world, pr);
            main = rooms.find(world, pr);
        }

        Arenas arenas = plugin.getModelManager().getArenas();
        Arena a = arenas.create(main);
        if (a == null) {
            sender.sendMessage(ChatColor.RED + "An arena already exists at that region.");
            return;
        }

        main.resetPurpose();
        sender.sendMessage(ChatColor.GREEN + "Arena created. Use " + ChatColor.YELLOW + "/arenasetspawn" + ChatColor.GREEN + " to add spawn points.");
    }

}
