/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.mattkoth;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import java.util.Map;

/**
 *
 * @author ian
 */
public class MattKOTH extends Gameplay<KOTHState> {

    public MattKOTH(CloudGame plugin) {
        super(plugin, "KOTH");
    }

    @Override
    public void setup(Game<KOTHState> g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KOTHState newState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onSend(Game<KOTHState> game, String type, Map<String, Object> message) {
        switch (type) {
            case "start":
                onStart();
                break;
        }
    }

    private void onStart() {

    }

}
