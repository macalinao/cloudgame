/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.gameplay;

import pw.ian.albkit.command.TreeCommandHandler;

/**
 *
 * @author ian
 */
public class GameplayCommand extends TreeCommandHandler {

    public GameplayCommand() {
        super("gameplay");
        setUsage("/gameplay <subcommand> [args...]");
        setDescription("Handles gameplay-related things.");
    }

    @Override
    public void setupSubcommands() {
        addSubcommand(new GameplayListCommand());
    }

}
