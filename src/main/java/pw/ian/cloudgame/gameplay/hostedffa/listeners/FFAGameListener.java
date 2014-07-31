/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import pw.ian.cloudgame.events.GameEndEvent;
import pw.ian.cloudgame.events.GameEventFactory;
import pw.ian.cloudgame.events.GameQuitEvent;
import pw.ian.cloudgame.events.GameStartEvent;
import pw.ian.cloudgame.events.GameStopEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.GameListener;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAState;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAWinner;

/**
 *
 * @author ian
 */
public class FFAGameListener extends GameListener<HostedFFAState> {

    public FFAGameListener(HostedFFA ffa) {
        super(ffa);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameStart(GameStartEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            getGameplay().sendBanner(p, "A " + getGameplay().getName() + " on map $D" + game.getArena().getName() + " $Lhas started!",
                    "Type $D/" + getGameplay().getId() + " spectate $Lto spectate it!");
        }

        HostedFFAState state = game.getState();
        for (Player p : state.getPlayers()) {
            Location spawn = game.getArena().getNextSpawn();
            if (state.isProvideArmor()) {
                getGameplay().getPlugin().getPlayerStateManager().saveState(p);
            }
            p.setGameMode(GameMode.ADVENTURE);
            p.teleport(spawn);
        }

        state.setStarted();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameEnd(GameEndEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }

        HostedFFAWinner winner = (HostedFFAWinner) event.getWinner();
        if (winner == null) {
            game.broadcast("Game over! Nobody won!");
        } else {
            game.broadcast("$H" + winner.getPlayer().getName() + "$M won the " + getGameplay().getName() + "!");
            getGameplay().sendGameMessage(winner, "To redeem your prize, type $H/" + getGameplay().getId() + " redeem$M!");
            ((HostedFFA) getGameplay()).addPrize(winner, game.getState().isProvideArmor() ? "easy" : "hard");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void postGameEnd(GameEndEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }
        game.events().stop();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameStop(GameStopEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }
        HostedFFAState state = game.getState();

        if (!state.isStarted()) {
            game.broadcast("The " + getGameplay().getName() + " has been cancelled.");
        } else {
            for (Player player : state.getSpectators()) {
                getGameplay().getPlugin().getPlayerStateManager().queueLoadState(player);
            }

            for (Player player : state.getParticipants()) {
                game.events().quit(player);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void postGameStop(GameStopEvent event) {
        Game<HostedFFAState> game = game(event);
        if (game == null) {
            return;
        }
        game.getState().setOver();
        getGameplay().getPlugin().getGameManager().removeGame(game);
        ((HostedFFA) getGameplay()).setGame(null);
    }

}
