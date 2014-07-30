/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.arena;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.CloudGame;
import static pw.ian.cloudgame.CloudGame.wg;
import pw.ian.albkit.command.CommandHandler;
import pw.ian.cloudgame.model.arena.Arena;
import pw.ian.cloudgame.model.arena.Arenas;
import pw.ian.cloudgame.model.region.Region;
import pw.ian.cloudgame.model.region.Regions;

/**
 *
 * @author ian
 */
public class ArenaCreateCommand extends CommandHandler {

    private final CloudGame cg;

    public ArenaCreateCommand(CloudGame plugin) {
        super(plugin, "create");
        this.cg = plugin;
        setUsage("/arena create <region> [world=current]");
        setDescription("Creates an arena.");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sendUsageMessage(sender);
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

        ProtectedRegion pr = wg().getRegionManager(world).getRegionExact(regionName);
        if (pr == null) {
            sender.sendMessage(ChatColor.RED + "The region '" + regionName + "' does not exist in the world '" + world.getName() + "'.");
            return;
        }

        Regions regions = cg.getModelManager().getRegions();
        Region main = regions.find(world, pr);
        if (main != null) {
            if (cg.getModelManager().getArenas().findByRegion(main) != null) {
                sender.sendMessage(ChatColor.RED + "That region is already associated with an arena. Try using a different region.");
                return;
            }
        } else {
            regions.create(world, pr);
            main = regions.find(world, pr);
        }

        Arenas arenas = cg.getModelManager().getArenas();
        Arena a = arenas.create(main);
        if (a == null) {
            sender.sendMessage(ChatColor.RED + "An arena already exists at that region.");
            return;
        }

        sender.sendMessage(ChatColor.GREEN + "Arena created. Use " + ChatColor.YELLOW + "/arenasetspawn" + ChatColor.GREEN + " to add spawn points.");
    }

}
