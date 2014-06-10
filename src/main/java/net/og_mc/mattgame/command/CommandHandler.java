/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattgame.command;

import net.og_mc.mattgame.MattGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author ian
 */
public abstract class CommandHandler implements CommandExecutor {

    protected final MattGame plugin;

    private Command command = null;

    public CommandHandler(MattGame plugin) {
        this.plugin = plugin;
    }

    protected void setCommand(Command command) {
        if (command == null) {
            this.command = command;
        }
    }

    public String usage() {
        return ChatColor.RED + "Usage: " + command.getUsage();
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
        this.onCommand(cs, args);
        return true;
    }

    public abstract void onCommand(CommandSender sender, String[] args);
}
