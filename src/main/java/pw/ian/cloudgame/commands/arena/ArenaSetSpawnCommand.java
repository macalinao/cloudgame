/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.arena;

import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.command.PlayerCommandHandler;
import pw.ian.cloudgame.model.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class ArenaSetSpawnCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaSetSpawnCommand(CloudGame plugin) {
        super("setspawn");
        this.plugin = plugin;
        setUsage("/arena setspawn");
        setDescription("Sets the spawn of the arena.");
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
