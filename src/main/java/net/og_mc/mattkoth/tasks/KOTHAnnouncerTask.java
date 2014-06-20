/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.tasks;

import com.simplyian.cloudgame.events.GameStartEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.util.Messaging;
import net.og_mc.mattkoth.KOTHState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author ian
 */
public class KOTHAnnouncerTask extends BukkitRunnable {

    private final Game<KOTHState> game;

    private final long start = System.currentTimeMillis();

    private int announceCount = 0;

    public KOTHAnnouncerTask(Game<KOTHState> game) {
        this.game = game;
    }

    @Override
    public void run() {
        if (game.getState().isStarted()) {
            cancel();
            return;
        }

        int secsLeft = 5 * 60 - (((int) (System.currentTimeMillis() - start)) / 1000);

        if (secsLeft <= 5 * 60 && announceCount == 0) {
            announceTime("5 minutes");
            announceCount++;
        } else if (secsLeft <= 3 * 60 && announceCount == 1) {
            announceTime("3 minutes");
            announceCount++;
        } else if (secsLeft <= 1 * 60 && announceCount == 2) {
            announceTime("1 minute");
            announceCount++;
        } else if (secsLeft <= 30 && announceCount == 3) {
            announceTime("30 seconds");
            announceCount++;
        } else if (secsLeft <= 10 && announceCount == 4) {
            announceTime("10 seconds");
            announceCount++;
        } else if (secsLeft <= 0 && announceCount == 5) {
            Bukkit.getPluginManager().callEvent(new GameStartEvent(game));
            cancel();
        }
    }

    private void announceTime(String time) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Messaging.sendBanner(p,
                    "A KOTH on map " + ChatColor.DARK_GREEN + game.getArena().getName() + " "
                    + ChatColor.GREEN + "is starting in " + ChatColor.DARK_GREEN + time + ChatColor.GREEN + "!",
                    "Type " + ChatColor.DARK_GREEN + "/koth join " + ChatColor.GREEN + "to join "
                    + ChatColor.DARK_GREEN + game.getState().getPlayers().size() + ChatColor.GREEN + " other players! (armor not provided)");
        }
    }

}
