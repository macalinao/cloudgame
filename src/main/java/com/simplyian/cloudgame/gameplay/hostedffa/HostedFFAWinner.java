package com.simplyian.cloudgame.gameplay.hostedffa;

import java.util.List;
import java.util.UUID;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Winner;

public class HostedFFAWinner extends Winner<HostedFFAState> {
    public HostedFFAWinner(Game<HostedFFAState> game, List<UUID> winners) {
        super(game, winners);
    }

    // TODO: Anything needed here?
}
