/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.hostedffa;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.gameplay.State;
import com.simplyian.cloudgame.gameplay.Winner;
import com.simplyian.cloudgame.gameplay.hostedffa.listeners.FFACommandListener;
import com.simplyian.cloudgame.gameplay.hostedffa.listeners.FFADeathListener;
import com.simplyian.cloudgame.gameplay.hostedffa.listeners.FFAGameListener;
import com.simplyian.cloudgame.gameplay.hostedffa.listeners.FFAGamePlayerListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 * @param <T>
 */
public abstract class HostedFFA<T extends HostedFFAState> extends Gameplay<T> {

    private Game<T> game;

    private final Map<Winner, String> prizes = new HashMap<>();

    protected HostedFFA(CloudGame plugin, String name) {
        super(plugin, name);
    }

    @Override
    public void onEnable() {
        getPlugin().getServer().getPluginManager().registerEvents(new FFACommandListener(this), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new FFAGameListener(this), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new FFAGamePlayerListener(this), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new FFADeathListener(this), getPlugin());
    }

    @Override
    public void setup(Game<T> g) {
        (new HostedFFAAnnouncerTask<>(g)).runTimer();
    }

    public Game<T> getGame() {
        return game;
    }

    public void setGame(Game<T> game) {
        this.game = game;
    }

    /**
     * Adds the winner to the list of people who deserve prizes.
     *
     * @param p
     * @param type
     */
    public void addPrize(Winner w, String type) {
        prizes.put(w, type);
    }

    /**
     * Tries to redeem a prize.
     *
     * @param w
     * @return
     */
    public boolean redeemPrize(Player p) {
    	for (Winner w : prizes.keySet()) {
    		if (!(w instanceof HostedFFAWinner)) {
    			continue;
    		}
    		if (((HostedFFAWinner) w).is(p.getUniqueId())) {
    			return redeemPrize(w);
    		}
    	}
    	return false;
    }

    /**
     * Tries to redeem a prize.
     *
     * @param w
     * @return
     */
    public boolean redeemPrize(Winner w) {
        if (!prizes.containsKey(w)) {
            return false;
        }
        if (!(w instanceof HostedFFAWinner)) {
        	return false;
        }
        String type = prizes.remove(w);
        ((HostedFFAWinner) w).givePrize(type);
        return true;
    }
}
