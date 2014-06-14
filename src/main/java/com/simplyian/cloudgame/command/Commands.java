/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.command;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.commands.arena.ArenaCreateCommand;
import com.simplyian.cloudgame.commands.arena.ArenaInfoCommand;
import com.simplyian.cloudgame.commands.arena.ArenaListSpawnsCommand;
import com.simplyian.cloudgame.commands.arena.ArenaResetSpawnsCommand;
import com.simplyian.cloudgame.commands.arena.ArenaSetNameCommand;
import com.simplyian.cloudgame.commands.arena.ArenaSetSpawnCommand;
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
        handler.setCommand(cmd);
    }

    public void registerDefaultCommands() {
        registerCommand("arenacreate", new ArenaCreateCommand(plugin));
        registerCommand("arenalistspawns", new ArenaInfoCommand(plugin));
        registerCommand("arenalistspawns", new ArenaListSpawnsCommand(plugin));
        registerCommand("arenaresetspawns", new ArenaResetSpawnsCommand(plugin));
        registerCommand("arenasetname", new ArenaSetNameCommand(plugin));
        registerCommand("arenasetspawn", new ArenaSetSpawnCommand(plugin));
    }
}
