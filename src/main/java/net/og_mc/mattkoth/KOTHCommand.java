/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.command.PlayerCommandHandler;
import com.simplyian.cloudgame.util.Messaging;
import org.bukkit.entity.Player;

/**
 * The main command for all that shizzzzle
 *
 * @author ian
 */
public class KOTHCommand extends PlayerCommandHandler {

    public KOTHCommand(CloudGame plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            noArgs(player);
            return;
        }

        switch (args[0].toLowerCase()) {
            case "join":
                join(player, args);
                return;
            case "leave":
                leave(player, args);
                return;
            case "start":
                start(player, args);
                return;
            case "setregion":
                setregion(player, args);
                return;
            case "setspawn":
                setspawn(player, args);
                return;
        }
    }

    private void noArgs(Player player) {
        // TODO add to help menu based on permissions
        Messaging.sendBanner(player,
                "/koth join - Join the koth",
                "/koth leave - Leaves the koth",
                "/koth start - Starts a KOTH if there isn't already one going on",
                "/koth setregion - Set the koth region",
                "/koth setspawn - Sets a spawn on the koth map");
    }

    private void join(Player player, String[] args) {

    }

    private void leave(Player player, String[] args) {

    }

    private void start(Player player, String[] args) {

    }

    private void setregion(Player player, String[] args) {

    }

    private void setspawn(Player player, String[] args) {

    }
}
