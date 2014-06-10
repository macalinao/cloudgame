/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.command;

import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.commands.SetRoomCommand;
import net.og_mc.mattgame.commands.UnsetRoomCommand;

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
        plugin.getCommand(command).setExecutor(handler);
    }

    public void registerDefaultCommands() {
        registerCommand("setroom", new SetRoomCommand(plugin));
        registerCommand("unsetroom", new UnsetRoomCommand(plugin));
    }
}
