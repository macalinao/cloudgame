/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.arena;

import com.google.common.base.Joiner;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.CloudGame;
import pw.ian.albkit.command.PlayerCommandHandler;
import pw.ian.cloudgame.model.arena.Arena;

/**
 *
 * @author ian
 */
public class ArenaSetNameCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaSetNameCommand(CloudGame plugin) {
        super("setname");
        this.plugin = plugin;
        setUsage("/arena setname");
        setDescription("Sets the name of the arena.");
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
            sendUsageMessage(player);
            return;
        }

        String name = Joiner.on(' ').join(args);
        a.setName(name);
        player.sendMessage(ChatColor.GREEN + "Arena name set to " + ChatColor.YELLOW + a.getName() + ChatColor.GREEN + ".");
    }

}
