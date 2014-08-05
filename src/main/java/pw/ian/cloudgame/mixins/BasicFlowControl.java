/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import pw.ian.cloudgame.events.GameStopEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Participants;
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
