/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.commands.hostedffa;

import com.simplyian.cloudgame.command.TreeCommandHandler;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFA;

/**
 * The main command for all that shizzzzle
 *
 * @author ian
 */
public class FFACommand extends TreeCommandHandler {

    private final HostedFFA ffa;

    public FFACommand(HostedFFA ffa) {
        super(ffa.getId());
        this.ffa = ffa;
    }

    @Override
    public void setupSubcommands() {
        addSubcommand("forcestart", new FFAForceStartCommand(ffa));
        addSubcommand("join", new FFAJoinCommand(ffa));
        addSubcommand("leave", new FFALeaveCommand(ffa));
        addSubcommand("redeem", new FFARedeemCommand(ffa));
        addSubcommand("setspawn", new FFASetSpawnCommand(ffa));
        addSubcommand("spectate", new FFASpectateCommand(ffa));
        addSubcommand("start", new FFAStartCommand(ffa));
        addSubcommand("stop", new FFAStopCommand(ffa));
    }
}
