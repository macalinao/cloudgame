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
import pw.ian.cloudgame.gameplay.State;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAState;
import pw.ian.cloudgame.mixin.Mixin;

/**
 * Prevents usage of commands.
 *
 * @param <T>
 */
public class NoCommands<T extends State> extends Mixin<T> {

    public NoCommands(Gameplay<T> gameplay) {
        super(gameplay);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Game<T> game = game(e.getPlayer());
        if (game != null && game.getState().isStarted() && !e.getMessage().startsWith("/" + getGameplay().getId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpectatorCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Game<T> game = gameSpectated(e.getPlayer());
        if (game != null && game.getState().isStarted()
                && !e.getMessage().startsWith("/" + getGameplay().getId()) && !e.getMessage().startsWith("/msg") && !e.getMessage().startsWith("/r ")) {
            e.setCancelled(true);
        }
    }

}
