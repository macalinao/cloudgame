/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.commands.hostedffa;

import com.simplyian.cloudgame.command.PlayerCommandHandler;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFA;
import com.simplyian.cloudgame.gameplay.hostedffa.HostedFFAState;
import com.simplyian.cloudgame.model.arena.Arena;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class FFAStartCommand extends PlayerCommandHandler {

    private final HostedFFA ffa;

    public FFAStartCommand(HostedFFA ffa) {
        super("start");
        this.ffa = ffa;
        setDescription("Starts a " + ffa.getId() + " if there isn't already one going on.");
        setUsage("/" + ffa.getId() + " start <arena>");
        setPermission("mattmg.admin");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (!player.hasPermission("HostedFFA.admin")) {
            ffa.sendGameMessage(player, "You can't use this command.");
            return;
        }

        if (args.length == 0) {
            ffa.sendGameMessage(player, "Usage: /" + ffa.getId() + " start <arena> [mins = 10]");
            return;
        }

        Arena arena = ffa.getPlugin().getModelManager().getArenas().find(player, args[0]);
        if (arena == null) {
            ffa.sendGameMessage(player, "That arena does not exist.");
            return;
        }

        if (ffa.getGame() != null) {
            ffa.sendGameMessage(player, "A game has already been started.");
            return;
        }

        Game<HostedFFAState> game = ffa.getPlugin().getGameManager().createGame(ffa, arena);
        if (game == null) {
            ffa.sendGameMessage(player, "FFA is not supported on the given arena.");
            return;
        }

        int mins = 10;
        if (args.length >= 2) {
            try {
                mins = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                ffa.sendGameMessage(player, "The number of minutes you specified isn't a valid number.");
                return;
            }
        }

        ffa.setGame(game);
        game.getState().setHost(player);
        game.getState().setMins(mins);
        player.teleport(game.getArena().getNextSpawn());
        ffa.sendGameMessage(player, "FFA countdown started for a " + mins + " minute FFA game.");
    }

}
