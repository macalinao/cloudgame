/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.arena;

import java.util.Map.Entry;
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
public class ArenaListSpawnsCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaListSpawnsCommand(CloudGame plugin) {
        super(plugin, "listspawns");
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
