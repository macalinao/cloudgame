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
public class ArenaInfoCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaInfoCommand(CloudGame plugin) {
        super("info");
        this.plugin = plugin;
        setUsage("/arena info");
        setDescription("Gets information about the arena.");
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
        player.sendMessage(ChatColor.GREEN + "Main: " + ChatColor.YELLOW + a.getMain().getId()
                + "    " + ChatColor.GREEN + "Spawns: " + ChatColor.YELLOW + a.getSpawns().size() + " - Use /arenalistspawns to show locations");
        player.sendMessage(ChatColor.YELLOW + "---------- PROPERTIES ----------");
        for (Entry<String, Object> property : a.getProperties().entrySet()) {
            player.sendMessage(ChatColor.GREEN + property.getKey() + ": " + ChatColor.YELLOW + property.getValue().toString());
        }
    }

}
