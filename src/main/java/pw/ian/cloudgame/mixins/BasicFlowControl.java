/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import pw.ian.cloudgame.events.GameEndEvent;
import pw.ian.cloudgame.events.GameStartEvent;
import pw.ian.cloudgame.events.GameStopEvent;
import pw.ian.cloudgame.events.GameUnspectateEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Participants;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAState;
import pw.ian.cloudgame.mixin.Mixin;
import pw.ian.cloudgame.states.Status;

/**
 *
 * @author ian
 */
public class BasicFlowControl extends Mixin {

    public BasicFlowControl(Gameplay gameplay) {
        super(gameplay);
    }

    @Override
    public void setup() {
        state(Status.class);
    }

    @EventHandler
    public void onGameUnspectate(GameUnspectateEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        game.getParticipants().removeSpectator(p);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameStart(GameStartEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        game.state(Status.class).setStarted(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameStop(GameStopEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Status status = game.state(Status.class);
        if (!status.isStarted()) {
            game.broadcast("The " + getGameplay().getName() + " has been cancelled.");
            return;
        }

        Participants participants = game.getParticipants();
        for (Player player : participants.getSpectators()) {
            getGameplay().getPlugin().getPlayerStateManager().queueLoadState(player);
        }

        for (Player player : participants.getParticipants()) {
            game.events().quit(player);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void postGameEnd(GameEndEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }
        game.events().stop();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void postGameStop(GameStopEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        game.state(Status.class).setOver(true);
        getGameplay().getPlugin().getGameManager().removeGame(game);
    }
}
