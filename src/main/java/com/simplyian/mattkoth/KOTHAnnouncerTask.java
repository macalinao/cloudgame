/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.mattkoth;

import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.util.Messaging;
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
        int secsLeft = 5 * 60 - (((int) (System.currentTimeMillis() - start)) / 1000);

        if (secsLeft <= 5 * 60 && announceCount == 0) {
            announce("5 minutes");
            announceCount++;
        } else if (secsLeft <= 3 * 60 && announceCount == 1) {
            announce("3 minutes");
            announceCount++;
        } else if (secsLeft <= 1 * 60 && announceCount == 2) {
            announce("1 minute");
            announceCount++;
        } else if (secsLeft <= 30 && announceCount == 3) {
            announce("30 seconds");
            announceCount++;
        } else if (secsLeft <= 10 && announceCount == 4) {
            announce("10 seconds");
            announceCount++;
        } else if (secsLeft <= 0 && announceCount == 5) {
            game.send("START", null);
            cancel();
        }
    }

    private void announce(String time) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Messaging.sendBanner(p,
                    "A KOTH on map " + ChatColor.DARK_GREEN + game.getArena().getName() + " "
                    + ChatColor.GREEN + "is starting in " + ChatColor.DARK_GREEN + time + ChatColor.DARK_GREEN + "!",
                    "Type " + ChatColor.DARK_GREEN + "/koth join " + ChatColor.GREEN + "to join! (armor not provided)");
        }
    }

}
