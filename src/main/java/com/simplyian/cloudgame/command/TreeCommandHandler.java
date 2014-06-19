/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.command.CommandSender;

/**
 *
 * @author ian
 */
public abstract class TreeCommandHandler extends CommandHandler {

    private final Map<String, CommandHandler> subcommands = new HashMap<>();

    /**
     * Sets up the subcommands in this command handler
     */
    public abstract void setupSubcommands();

    /**
     * Called when the command has no arguments.
     *
     * @param sender
     */
    public abstract void onCommandNoArgs(CommandSender sender);

    /**
     * Called when the command has an invalid subcommand name.
     *
     * @param sender
     */
    public abstract void onCommandInvalidArgs(CommandSender sender);

    /**
     * Adds a subcommand to this tree command handler
     *
     * @param name
     * @param handler
     */
    protected void addSubcommand(String name, CommandHandler handler) {
        subcommands.put(name.toLowerCase(), handler);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            onCommandNoArgs(sender);
            return;
        }

        CommandHandler handler = subcommands.get(args[0]);
        if (handler != null) {
            handler.onCommand(sender, Arrays.copyOfRange(args, 1, args.length));
            return;
        }

        onCommandInvalidArgs(sender);
    }

}
