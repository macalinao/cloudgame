/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import pw.ian.cloudgame.events.GameQuitEvent;
import pw.ian.cloudgame.events.GameStartEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Mixin;

/**
 * Gives players a temporary inventory for the games. The old inventory is
 * stored and restored after the game.
 */
public class TransientInventories extends Mixin {

    public TransientInventories(Gameplay gameplay) {
        super(gameplay);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameStart(GameStartEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        for (Player p : game.getParticipants().getPlayers()) {
            getGameplay().getPlugin().getPlayerStateManager().saveState(p);
            p.setGameMode(GameMode.ADVENTURE);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGameQuit(GameQuitEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        getGameplay().getPlugin().getPlayerStateManager().queueLoadState(event.getPlayer());
        p.setGameMode(GameMode.SURVIVAL);
    }

}
