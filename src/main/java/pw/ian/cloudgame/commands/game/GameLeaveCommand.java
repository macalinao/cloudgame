/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.game;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pw.ian.albkit.command.PlayerCommandHandler;
import pw.ian.albkit.command.parser.Arguments;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.game.Game;

/**
 *
 * @author ian
 */
public class GameLeaveCommand extends PlayerCommandHandler {

    public GameLeaveCommand() {
        super("leave");
        setDescription("Leaves a game.");
        setUsage("/game leave");
    }

    @Override
    public void onCommand(Player player, Arguments args) {
        Game game = CloudGame.inst().getGameManager().gameOf(player);
        if (game == null) {
            player.sendMessage(ChatColor.RED + "You aren't in a game.");
        }

        game.events().leave(player);
    }

}
