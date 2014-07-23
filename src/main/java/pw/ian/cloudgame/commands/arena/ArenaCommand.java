/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.arena;

import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.command.TreeCommandHandler;

/**
 *
 * @author ian
 */
public class ArenaCommand extends TreeCommandHandler {

    private final CloudGame plugin;

    public ArenaCommand(CloudGame cloudGame) {
        super("arena");
        this.plugin = cloudGame;
        setUsage("/arena <subcommand> [args]");
        setDescription("Allows managing arenas.");
    }

    @Override
    public void setupSubcommands() {
        addSubcommand("create", new ArenaCreateCommand(plugin));
        addSubcommand("delete", new ArenaDeleteCommand(plugin));
        addSubcommand("info", new ArenaInfoCommand(plugin));
        addSubcommand("listspawns", new ArenaListSpawnsCommand(plugin));
        addSubcommand("reload", new ArenaReloadCommand(plugin));
        addSubcommand("resetspawns", new ArenaResetSpawnsCommand(plugin));
        addSubcommand("setname", new ArenaSetNameCommand(plugin));
        addSubcommand("setspawn", new ArenaSetSpawnCommand(plugin));
    }

}
