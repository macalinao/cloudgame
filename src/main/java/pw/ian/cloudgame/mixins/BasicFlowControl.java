/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import pw.ian.cloudgame.events.GameEndEvent;
import pw.ian.cloudgame.events.GameQuitEvent;
import pw.ian.cloudgame.events.GameStartEvent;
import pw.ian.cloudgame.events.GameStopEvent;
import pw.ian.cloudgame.events.GameUnspectateEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Participants;
import pw.ian.cloudgame.gameplay.Mixin;
import pw.ian.cloudgame.states.Status;

/**
 * A mixin which controls the basic 'flow' of a game. This involves handling
 * players joining, quitting and spectating the game and calling events for when
 * the game starts or ends.
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

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameQuit(GameQuitEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        game.getParticipants().removePlayer(p);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameSpectate(GameUnspectateEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        game.getParticipants().addSpectator(p);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameUnspectate(GameUnspectateEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Player p = event.getPlayer();
        game.getParticipants().removeSpectator(p);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameStart(GameStartEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        game.state(Status.class).setStarted();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameEnd(GameEndEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }
        game.events().stop();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleGameStop(GameStopEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        game.state(Status.class).setOver(true);
        getGameplay().getPlugin().getGameManager().removeGame(game);
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
}
