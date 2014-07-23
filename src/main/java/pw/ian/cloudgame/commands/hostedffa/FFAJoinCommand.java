/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.hostedffa;

import pw.ian.cloudgame.command.PlayerCommandHandler;
import pw.ian.cloudgame.events.GameJoinEvent;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class FFAJoinCommand extends PlayerCommandHandler {

    private final HostedFFA ffa;

    public FFAJoinCommand(HostedFFA ffa) {
        super("join");
        this.ffa = ffa;
        setDescription("Joins the " + ffa.getName() + ".");
        setUsage("/" + ffa.getId() + " join");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (ffa.getGame() == null) {
            ffa.sendGameMessage(player, "There isn't a game going on right now.");
            return;
        }
        Bukkit.getPluginManager().callEvent(new GameJoinEvent(ffa.getGame(), player));
    }

}
