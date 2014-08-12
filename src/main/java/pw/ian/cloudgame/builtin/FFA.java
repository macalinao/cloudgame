package pw.ian.cloudgame.builtin;

import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;

/**
 * Generic FFA Gameplay
 */
public class FFA extends HostedFFA { // in the future, we should have a HostedFFA mixin that depends on everything

    public FFA() {
        super(CloudGame.inst(), "ffa");
        setDescription("A simple free-for-all game mode.");
    }

}
