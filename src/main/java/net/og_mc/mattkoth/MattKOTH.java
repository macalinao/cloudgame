/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import net.og_mc.mattgame.MattGame;
import net.og_mc.mattgame.game.Game;
import net.og_mc.mattgame.gameplay.Gameplay;
import net.og_mc.mattgame.gameplay.State;

/**
 *
 * @author ian
 */
public class MattKOTH extends Gameplay {

    public MattKOTH(MattGame plugin) {
        super(plugin, "KOTH");
    }

    @Override
    public void setup(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State newState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
