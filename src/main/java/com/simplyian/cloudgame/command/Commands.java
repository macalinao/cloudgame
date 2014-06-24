/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.command;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.commands.arena.ArenaCommand;
import org.bukkit.command.PluginCommand;

/**
 *
 * @author ian
 */
public class Commands {

    private final CloudGame plugin;

    public Commands(CloudGame plugin) {
        this.plugin = plugin;
    }

    public void registerCommand(String command, CommandHandler handler) {
        PluginCommand cmd = plugin.getCommand(command);
        cmd.setExecutor(handler);
    }

    public void registerDefaultCommands() {
        registerCommand("arena", new ArenaCommand(plugin));
    }
}
