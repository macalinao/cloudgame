package pw.ian.cloudgame.builtin;

import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.mixins.HostedFFA;

/**
 * Generic FFA Gameplay
 */
public class FFA extends Gameplay {

    public FFA() {
        super(CloudGame.inst(), "ffa");
        setDescription("A simple free-for-all game mode.");
    }

    @Override
    public void setup(Game g) {
        mixin(HostedFFA.class);
    }

}
