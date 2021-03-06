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
public class GameStartCommand extends PlayerCommandHandler {

    public GameStartCommand() {
        super("start");
        setDescription("Forces a game to start.");
        setUsage("/game start <game>");
        setPermission("mattmg.admin");
    }

    @Override
    public void onCommand(Player player, Arguments args) {
        if (args.length() == 0) {
            sendUsageMessage(player);
            return;
        }

        Game game = CloudGame.inst().getGameManager().gameAt(args.getArgument(0).rawString());
        if (game == null) {
            player.sendMessage(ChatColor.RED + "Game not found.");
            return;
        }

        game.events().start();
    }

}
