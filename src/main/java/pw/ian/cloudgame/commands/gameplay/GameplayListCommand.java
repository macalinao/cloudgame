/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.gameplay;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import pw.ian.albkit.command.CommandHandler;
import pw.ian.albkit.command.parser.Arguments;
import pw.ian.albkit.util.ColorScheme;
import pw.ian.albkit.util.Messaging;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.gameplay.Gameplay;

/**
 *
 * @author ian
 */
public class GameplayListCommand extends CommandHandler {

    private final CloudGame cg = CloudGame.inst();

    public GameplayListCommand() {
        super("list");

        setDescription("Lists available gameplays.");
        setUsage("/gameplay list");
    }

    @Override
    public void onCommand(CommandSender sender, Arguments args) {
        List<String> memo = new ArrayList<>();
        for (Gameplay gp : cg.getGameplayManager().getGameplays()) {
            memo.add("$H" + gp.getName() + " - $M" + gp.getDescription());
        }
        if (memo.size() > 0) {
            Messaging.sendBanner(ColorScheme.DEFAULT, sender, memo.toArray(new String[0]));
            return;
        }

        sender.sendMessage(ChatColor.RED + "There are no gameplays currently registered on the server.");
    }

}
