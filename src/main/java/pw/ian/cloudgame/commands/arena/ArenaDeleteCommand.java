/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.arena;

import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.command.PlayerCommandHandler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class ArenaDeleteCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaDeleteCommand(CloudGame plugin) {
        super("delete");
        this.plugin = plugin;
        setUsage("/arena delete");
        setDescription("Deletes an arena.");
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
