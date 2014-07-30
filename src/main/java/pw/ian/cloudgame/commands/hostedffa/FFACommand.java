/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.hostedffa;

import pw.ian.albkit.command.TreeCommandHandler;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;

/**
 * The main command for all that shizzzzle (naughty ian)
 *
 * @author ian
 */
public class FFACommand extends TreeCommandHandler {

    private final HostedFFA ffa;

    public FFACommand(HostedFFA ffa) {
        super(ffa.getPlugin(), ffa.getId());
        this.ffa = ffa;
        setColorScheme(ffa.getColorScheme());
    }

    @Override
    public void setupSubcommands() {
        addSubcommand("forcestart", new FFAForceStartCommand(ffa));
        addSubcommand("leave", new FFALeaveCommand(ffa));
        addSubcommand("redeem", new FFARedeemCommand(ffa));
        addSubcommand("spectate", new FFASpectateCommand(ffa));
        addSubcommand("start", new FFAStartCommand(ffa));
        addSubcommand("stop", new FFAStopCommand(ffa));
    }
}
