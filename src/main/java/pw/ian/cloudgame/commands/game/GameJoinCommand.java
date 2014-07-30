/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.ian.albkit.command.PlayerCommandHandler;
import pw.ian.albkit.command.parser.Arguments;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.events.GameJoinEvent;
import pw.ian.cloudgame.game.Game;

/**
 *
 * @author ian
 */
public class GameJoinCommand extends PlayerCommandHandler {

    public GameJoinCommand() {
        super(CloudGame.inst(), "join");
        setDescription("Joins a game.");
        setUsage("/game join <id>");
    }

    @Override
    public void onCommand(Player player, Arguments args) {
        if (args.length() == 0) {
            sendUsageMessage(player);
            return;
        }

        Game<?> game = CloudGame.inst().getGameManager().gameAt(args.getArgument(0).rawString());
        Bukkit.getPluginManager().callEvent(new GameJoinEvent(game, player));
    }

}
