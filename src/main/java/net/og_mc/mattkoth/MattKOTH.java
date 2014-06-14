/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

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
        (new KOTHAnnouncerTask(g)).runTaskTimer(getPlugin(), 2L, 2L);
    }

    @Override
    public void onReceive(Game<KOTHState> game, String type, Map<String, Object> message) {
        switch (type) {
            case "START":
                onStart();
                break;
        }
    }

    private void onStart() {

    }

}
