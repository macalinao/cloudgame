/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import pw.ian.cloudgame.events.GameEndEvent;
import pw.ian.cloudgame.events.GameStartEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.GameListener;
import pw.ian.cloudgame.gameplay.Participants;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAWinner;

/**
 *
 * @author ian
 */
public class FFAGameListener extends GameListener {

    public FFAGameListener(HostedFFA ffa) {
        super(ffa);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameStart(GameStartEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        Participants state = game.getParticipants();
        for (Player p : state.getPlayers()) {
            Location spawn = game.getArena().getNextSpawn();
            p.teleport(spawn);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void preGameEnd(GameEndEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        HostedFFAWinner winner = (HostedFFAWinner) event.getWinner();
        if (winner == null) {
            game.broadcast("Game over! Nobody won!");
        } else {
            game.broadcast("$H" + winner.getPlayer().getName() + "$M won the " + getGameplay().getName() + "!");
            getGameplay().sendGameMessage(winner, "To redeem your prize, type $H/" + getGameplay().getId() + " redeem$M!");
            ((HostedFFA) getGameplay()).addPrize(winner, "easy"); // TODO make prizes a mixin
        }
    }

}
