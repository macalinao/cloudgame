/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.handlers;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.MessageHandler;
import static com.sk89q.worldguard.bukkit.WGBukkit.getPlugin;
import java.util.Map;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.KOTHTimer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class StartHandler implements MessageHandler<KOTHState> {

    private final CloudGame plugin;

    public StartHandler(CloudGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onReceive(Game<KOTHState> game, Map<String, Object> message) {
        KOTHState state = game.getState();
        for (Player p : state.getPlayers()) {
            plugin.getInventoryManager().backupInventory(p);

            Location spawn = game.getArena().getNextSpawn();
            p.teleport(spawn);
        }

        state.setStarted(true);
        (new KOTHTimer(game)).runTaskTimer(plugin, 20L * 10, 20L * 10);
    }

}
