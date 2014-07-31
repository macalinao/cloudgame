/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.game;

import pw.ian.albkit.command.TreeCommandHandler;

/**
 *
 * @author ian
 */
public class GameCommand extends TreeCommandHandler {

    public GameCommand() {
        super("game");
        setUsage("/game <subcommand> [args...]");
        setDescription("Handles games.");
    }

    @Override
    public void setupSubcommands() {
        addSubcommand(new GameJoinCommand());
        addSubcommand(new GameLeaveCommand());
        addSubcommand(new GameSpectateCommand());
        addSubcommand(new GameStartCommand());
        addSubcommand(new GameStopCommand());
    }

}
