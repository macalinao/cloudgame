/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.commands.arena;

import java.util.Map.Entry;
import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.command.PlayerCommandHandler;
import com.simplyian.cloudgame.model.arena.Arena;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class ArenaReloadCommand extends PlayerCommandHandler {

    private final CloudGame plugin;

    public ArenaReloadCommand(CloudGame plugin) {
        super("reload");
        this.plugin = plugin;
        setUsage("/arena reload");
        setDescription("Reloads all arenas from the arenas.yml");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        try {
            plugin.getModelManager().getArenas().load();
        } catch (IOException ex) {
            Logger.getLogger(ArenaReloadCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        player.sendMessage(ChatColor.YELLOW + "Arenas reloaded.");
    }

}
