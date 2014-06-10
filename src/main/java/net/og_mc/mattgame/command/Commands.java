/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.command;

import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.commands.arena.ArenaCreateCommand;
import net.og_mc.mattgame.commands.room.RoomSetCommand;
import net.og_mc.mattgame.commands.room.RoomUnsetCommand;
import org.bukkit.command.PluginCommand;

/**
 *
 * @author ian
 */
public class Commands {

    private final MattGame plugin;

    public Commands(MattGame plugin) {
        this.plugin = plugin;
    }

    public void registerCommand(String command, CommandHandler handler) {
        PluginCommand cmd = plugin.getCommand(command);
        cmd.setExecutor(handler);
        handler.setCommand(cmd);
    }

    public void registerDefaultCommands() {
        registerCommand("arenacreate", new ArenaCreateCommand(plugin));
        registerCommand("arenasetspawn", new ArenaCreateCommand(plugin));

        registerCommand("roomset", new RoomSetCommand(plugin));
        registerCommand("roomunset", new RoomUnsetCommand(plugin));
    }
}
