/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.game;

import pw.ian.albkit.command.TreeCommandHandler;
import pw.ian.cloudgame.CloudGame;

/**
 *
 * @author ian
 */
public class GameCommand extends TreeCommandHandler {

    private final CloudGame plugin;

    public GameCommand(CloudGame plugin) {
        super(plugin, "game");
        this.plugin = plugin;
        setUsage("/game <subcommand> [args...]");
        setDescription("Handles games.");
    }

    @Override
    public void setupSubcommands() {
        
    }

}
