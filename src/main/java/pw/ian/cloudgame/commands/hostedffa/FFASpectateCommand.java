/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.hostedffa;

import pw.ian.cloudgame.command.PlayerCommandHandler;
import pw.ian.cloudgame.events.GameSpectateEvent;
import pw.ian.cloudgame.events.GameUnspectateEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class FFASpectateCommand extends PlayerCommandHandler {

    private final HostedFFA ffa;

    public FFASpectateCommand(HostedFFA ffa) {
        super("spectate");
        this.ffa = ffa;
        setDescription("Spectates the " + ffa.getName() + ".");
        setUsage("/" + ffa.getId() + " spectate");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (ffa.getGame() == null) {
            ffa.sendGameMessage(player, "There is currently no game to spectate.");
            return;
        }

        if (((Game<HostedFFAState>) ffa.getGame()).getState().hasSpectator(player)) {
            Bukkit.getPluginManager().callEvent(new GameUnspectateEvent(ffa.getGame(), player));
        } else {
            Bukkit.getPluginManager().callEvent(new GameSpectateEvent(ffa.getGame(), player));
        }
    }

}
