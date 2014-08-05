/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.mixin.Mixin;
import pw.ian.cloudgame.states.Status;

/**
 * Prevents usage of commands.
 */
public class NoCommands extends Mixin {

    public NoCommands(Gameplay gameplay) {
        super(gameplay);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Game game = game(e.getPlayer());
        if (game != null && game.state(Status.class).isStarted() && !e.getMessage().startsWith("/" + getGameplay().getId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpectatorCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Game game = gameSpectated(e.getPlayer());
        if (game != null && game.state(Status.class).isStarted()
                && !e.getMessage().startsWith("/" + getGameplay().getId()) && !e.getMessage().startsWith("/msg") && !e.getMessage().startsWith("/r ")) {
            e.setCancelled(true);
        }
    }

}
