/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.handlers;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.MessageHandler;
import com.simplyian.cloudgame.util.Messaging;
import static com.sk89q.worldguard.bukkit.WGBukkit.getPlugin;
import java.util.Map;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.KOTHTimer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class JoinHandler implements MessageHandler<KOTHState> {

    private final CloudGame plugin;

    public JoinHandler(CloudGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onReceive(Game<KOTHState> game, Map<String, Object> message) {
        KOTHState state = game.getState();
        Player p = (Player) message.get("player");

        if (state.isStarted()) {
            game.getGameplay().sendGameMessage(p, "You can't join a KOTH that is already in progress.");
            return;
        }

        state.addPlayer(p);
        Messaging.sendBanner(p, "You've joined the KOTH! Pay attention to the countdown.",
                "Want to leave the game? Type " + ChatColor.DARK_GREEN + "/koth leave" + ChatColor.GREEN + "!");
    }

}
