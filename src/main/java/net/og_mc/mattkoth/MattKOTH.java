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
    }

    private void onJoin(Game<KOTHState> game, Map<String, Object> message) {
        Player p = (Player) message.get("player");

        game.getState().addPlayer(p);
        Messaging.sendBanner(p, "You've joined the KOTH! Pay attention to the countdown.",
                "Want to leave the game? Type " + ChatColor.DARK_GREEN + "/koth leave" + ChatColor.GREEN + "!");
    }

    private void onLeave(Game<KOTHState> game, Map<String, Object> message) {
        Player p = (Player) message.get("player");

        game.getState().removePlayer(p);
        p.sendMessage(ChatColor.GREEN + "You've left the KOTH. To rejoin, type " + ChatColor.DARK_GREEN + "/koth join" + ChatColor.GREEN + "!");
    }

}
