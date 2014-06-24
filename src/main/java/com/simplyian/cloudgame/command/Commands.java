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

    public void registerCommand(CommandHandler handler) {
        registerCommand(handler.getName(), handler);
    }

    public void registerCommand(String name, CommandHandler handler) {
        PluginCommand cmd = plugin.getCommand(name);
        cmd.setExecutor(handler);
    }

    public void registerDefaultCommands() {
        registerCommand(new ArenaCommand(plugin));
    }
}
