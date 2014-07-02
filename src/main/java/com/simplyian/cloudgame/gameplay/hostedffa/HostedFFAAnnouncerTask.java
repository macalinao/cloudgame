/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.hostedffa;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.simplyian.cloudgame.events.GameStartEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Timer;

/**
 * Announces the game and starts it when the time is up.
 *
 * @param <T>
 */
public class HostedFFAAnnouncerTask<T extends HostedFFAState> extends Timer<T> {
    private static final Map<Integer, String> messages = new HashMap<>();

    static {
        messages.put(5 * 60, "5 minutes");
        messages.put(3 * 60, "3 minutes");
        messages.put(1 * 60, "1 minute");
        messages.put(30, "30 seconds");
        messages.put(10, "10 seconds");
    }

    private final Game<T> game;

    public HostedFFAAnnouncerTask(Game<T> game) {
        super(game, messages);
        this.game = game;
    }

    @Override
    protected void announceTime(String time) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            game.getGameplay().sendBanner(p,
                    "A " + game.getGameplay().getName() + " on map $D" + game.getArena().getName() + " $Lis starting in $D" + time + "$L!",
                    "Type $D/" + game.getGameplay().getId() + " join $Lto join $D"
                    + game.getState().getPlayers().size() + " $Lother players! "
                    + (game.getState().isEasy() ? "(armor provided)" : ChatColor.RED + "(armor not provided)"));
        }
    }

}
