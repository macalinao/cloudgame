/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author ian
 */
public abstract class TreeCommandHandler extends CommandHandler {

    private final Map<String, CommandHandler> subcommands = new HashMap<>();

    public TreeCommandHandler(String name) {
        super(name);
        setupSubcommands();
    }

    /**
     * Sets up the subcommands in this command handler
     */
    public abstract void setupSubcommands();

    /**
     * Called when the command has no arguments.
     *
     * @param sender
     */
    public void onCommandNoArgs(CommandSender sender) {
        sendHelpMenu(sender);
    }

    /**
     * Called when the command has an invalid subcommand name.
     *
     * @param sender
     */
    public void onCommandInvalidArgs(CommandSender sender) {
        sendHelpMenu(sender);
    }

    /**
     * Sends the given player a help menu about the command.
     *
     * @param sender
     */
    public void sendHelpMenu(CommandSender sender) {
        for (CommandHandler handler : subcommands.values()) {
            if (handler.getPermission() == null
                    || sender.hasPermission(handler.getPermission())) {
                sender.sendMessage(ChatColor.GREEN + "/" + getName() + " " + handler.getName() + " - "
                        + ChatColor.YELLOW + handler.getDescription());
            }
        }
    }

    /**
     * Adds a subcommand to this tree command handler
     *
     * @param handler
     */
    protected void addSubcommand(CommandHandler handler) {
        addSubcommand(handler.getName(), handler);
    }

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
