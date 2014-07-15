/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.commands.hostedffa;

import com.simplyian.cloudgame.command.PlayerCommandHandler;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFA;
import com.simplyian.cloudgame.model.arena.Arena;
import com.simplyian.cloudgame.model.region.Region;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class FFASetRegionCommand extends PlayerCommandHandler {

    private final HostedFFA ffa;

    public FFASetRegionCommand(HostedFFA ffa) {
        super("setregion");
        this.ffa = ffa;
        setDescription("Sets a " + ffa.getName() + " arena.");
        setUsage("/" + ffa.getId() + " setregion <arena>");
        setPermission("mattmg.admin");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (!player.hasPermission("mattmg.admin")) {
            ffa.sendGameMessage(player, "You can't use this command.");
            return;
        }

        if (args.length == 0) {
            sendUsageMessage(player);
            return;
        }

        Arena arena = ffa.getPlugin().getModelManager().getArenas().find(player, args[0]);
        if (arena == null) {
            Region arenaRegion = null;
            if (args[0].contains(";")) {
                arenaRegion = ffa.getPlugin().getModelManager().getRegions().findById(args[0]);
            } else {
                arenaRegion = ffa.getPlugin().getModelManager().getRegions().find(player.getWorld(), args[0]);
            }

            if (arenaRegion == null) {
                ffa.sendGameMessage(player, "Invalid arena region.");
                return;
            }

            arena = ffa.getPlugin().getModelManager().getArenas().create(arenaRegion);
            arena.setName(arenaRegion.getRegion().getId());
            ffa.sendGameMessage(player, "The arena did not exist, so one was created on that region.");
        }

        ffa.sendGameMessage(player, "The arena at '" + ChatColor.YELLOW + arena.getId() + "' has been created.");
    }

}
