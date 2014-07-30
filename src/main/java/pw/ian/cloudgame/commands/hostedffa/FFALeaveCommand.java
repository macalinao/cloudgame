/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.hostedffa;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.ian.albkit.command.PlayerCommandHandler;
import pw.ian.cloudgame.events.GameLeaveEvent;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;

/**
 *
 * @author ian
 */
public class FFALeaveCommand extends PlayerCommandHandler {

    private final HostedFFA ffa;

    public FFALeaveCommand(HostedFFA ffa) {
        super(ffa.getPlugin(), "leave");
        this.ffa = ffa;
        setDescription("Leaves the " + ffa.getName() + ".");
        setUsage("/" + ffa.getId() + " leave");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (ffa.getGame() == null) {
            ffa.sendGameMessage(player, "There isn't a game going on right now.");
            return;
        }
        Bukkit.getPluginManager().callEvent(new GameLeaveEvent(ffa.getGame(), player));
    }

}
