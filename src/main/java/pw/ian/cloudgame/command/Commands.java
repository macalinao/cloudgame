/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.command;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Command registration class
 *
 * @author ian
 */
public class Commands {

    /**
     * C'tor
     *
     * @param cg
     */
    private Commands() {
    }

    /**
     * Registers a command handler.
     *
     * @param plugin
     * @param handler
     */
    public static void registerCommand(JavaPlugin plugin, CommandHandler handler) {
        registerCommand(plugin, handler.getName(), handler);
    }

    /**
     * Registers a command handler with a custom name.
     *
     * @param plugin
     * @param name
     * @param handler
     */
    public static void registerCommand(JavaPlugin plugin, String name, CommandHandler handler) {
        PluginCommand cmd = plugin.getCommand(name);
        cmd.setExecutor(handler);
        if (handler instanceof TreeCommandHandler) {
            ((TreeCommandHandler) handler).setupSubcommands();
        }
    }
}
