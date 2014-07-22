/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.command;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.commands.arena.ArenaCommand;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author ian
 */
public class Commands {

    private final JavaPlugin plugin;
    private final CloudGame cg;

    public Commands(JavaPlugin plugin) {
        this.plugin = plugin;

        cg = (CloudGame) plugin.getServer().getPluginManager().getPlugin("CloudGame");
    }

    public void registerCommand(CommandHandler handler) {
        registerCommand(handler.getName(), handler);
    }

    public void registerCommand(String name, CommandHandler handler) {
        PluginCommand cmd = plugin.getCommand(name);
        cmd.setExecutor(handler);
        if (handler instanceof TreeCommandHandler) {
            ((TreeCommandHandler) handler).setupSubcommands();
        }
    }

    public void registerDefaultCommands() {
        registerCommand(new ArenaCommand(cg));
    }
}
