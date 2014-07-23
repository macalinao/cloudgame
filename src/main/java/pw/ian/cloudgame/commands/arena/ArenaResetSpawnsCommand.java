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
public class ArenaResetSpawnsCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaResetSpawnsCommand(CloudGame plugin) {
        super("resetspawns");
        this.plugin = plugin;
        setUsage("/arena resetspawns");
        setDescription("Resets the spawns of the arena.");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        Location loc = player.getLocation();
        Arena a = plugin.getModelManager().getArenas().find(loc);
        if (a == null) {
            player.sendMessage(ChatColor.RED + "There is no arena where you are standing.");
            return;
        }

        a.resetSpawns();
        player.sendMessage(ChatColor.GREEN + "Spawns have been reset in the arena " + ChatColor.YELLOW + a.getId() + ChatColor.GREEN + ".");
    }

}
