/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.mixin.Mixin;

/**
 *
 * @author ian
 */
public class QuitOnDeath extends Mixin {

    public QuitOnDeath(Gameplay gameplay) {
        super(gameplay);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Game game = game(p);
        if (game == null || !game.getState().isStarted()) {
            return;
        }

        game.events().quit(p);
    }
}
