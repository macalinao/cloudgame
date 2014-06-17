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
 * Handles when a player quits the game while in game
 *
 * @author ian
 */
public class QuitHandler implements MessageHandler<KOTHState> {

    private final CloudGame plugin;

    public QuitHandler(CloudGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onReceive(Game<KOTHState> game, Map<String, Object> message) {
        Player player = (Player) message.get("player");
        game.getState().removePlayer(player);
    }

}
