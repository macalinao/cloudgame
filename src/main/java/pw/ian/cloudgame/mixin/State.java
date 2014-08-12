/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixin;

import pw.ian.cloudgame.game.Game;

/**
 * Holds data.
 */
public abstract class State {

    protected Game game = null;

    /**
     * Sets the game of this State.
     *
     * @param game
     * @return
     */
    State withGame(Game game) {
        if (this.game == null) {
            this.game = game;
        }
        return this;
    }
}
