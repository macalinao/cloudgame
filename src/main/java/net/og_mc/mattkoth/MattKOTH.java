/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.util.Messaging;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class MattKOTH extends Gameplay<KOTHState> {

    public MattKOTH(CloudGame plugin) {
        super(plugin, "KOTH");
    }

    @Override
    public void setup(Game<KOTHState> g) {
        (new KOTHAnnouncerTask(g)).runTaskTimer(getPlugin(), 2L, 2L);
    }

    @Override
    public void onReceive(Game<KOTHState> game, String type, Map<String, Object> message) {
        switch (type) {
            case "START":
                onStart(game, message);
                break;
            case "JOIN":
                onJoin(game, message);
                break;
            case "LEAVE":
                onLeave(game, message);
                break;
        }
    }

    private void onStart(Game<KOTHState> game, Map<String, Object> message) {
        KOTHState state = game.getState();
        for (Player p : state.getOnlinePlayers()) {
            Location spawn = game.getArena().getNextSpawn();
            p.teleport(spawn);
        }

        state.setStarted(true);
    }

    private void onJoin(Game<KOTHState> game, Map<String, Object> message) {
        KOTHState state = game.getState();
        Player p = (Player) message.get("player");

        if (state.isStarted()) {
            sendGameMessage(p, "You can't join a KOTH that is already in progress.");
            return;
        }

        state.addPlayer(p);
        Messaging.sendBanner(p, "You've joined the KOTH! Pay attention to the countdown.",
                "Want to leave the game? Type " + ChatColor.DARK_GREEN + "/koth leave" + ChatColor.GREEN + "!");
    }

    private void onLeave(Game<KOTHState> game, Map<String, Object> message) {
        KOTHState state = game.getState();
        Player p = (Player) message.get("player");

        if (!state.isStarted()) {
            game.getState().removePlayer(p);
            sendGameMessage(p, "You've left the KOTH. To rejoin, type " + ChatColor.YELLOW + "/koth join" + ChatColor.RED + "!");
            return;
        }

        // Kills check
        boolean failedKillsCheck = false;
        // TODO

        // Distance check
        boolean failedDistanceCheck = false;
        for (Player player : state.getOnlinePlayers()) {
            if (p.getLocation().distanceSquared(player.getLocation()) < 20 * 20) {
                failedDistanceCheck = true;
                break;
            }
        }

        if (failedKillsCheck) {
            sendGameMessage(p, "You must kill at least one person before leaving!");
        }
        if (failedDistanceCheck) {
            sendGameMessage(p, "You must be at least 20 blocks away from another player!");
        }

        if (!failedKillsCheck && !failedDistanceCheck) {
            state.removePlayer(p);
            sendGameMessage(p, "You have left the game.");
        }
    }

    @Override
    public void handleQuit(Game<KOTHState> game, Player player) {
        game.getState().removePlayer(player);
        // TODO add complicated combat logging stuff
    }

}
