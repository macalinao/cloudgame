/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa;

import pw.ian.cloudgame.hosted.HostedGameCountdown;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Winner;
import pw.ian.cloudgame.gameplay.hostedffa.listeners.FFACommandListener;
import pw.ian.cloudgame.gameplay.hostedffa.listeners.FFADeathListener;
import pw.ian.cloudgame.gameplay.hostedffa.listeners.FFAGameListener;
import pw.ian.cloudgame.gameplay.hostedffa.listeners.FFAGamePlayerListener;

/**
 *
 * @author ian
 * @param <T>
 */
public abstract class HostedFFA<T extends HostedFFAState> extends Gameplay<T> {

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
        (new HostedGameCountdown<T>(g)).runTimer();
    }

    /**
     * Adds the winner to the list of people who deserve prizes.
     *
     * @param w
     * @param type
     */
    public void addPrize(Winner w, String type) {
        prizes.put(w, type);
    }

    /**
     * Tries to redeem a prize.
     *
     * @param p
     * @return
     */
    public boolean redeemPrize(Player p) {
    	for (Winner w : prizes.keySet()) {
    		if (((HostedFFAWinner) w).getPlayerId().equals(p.getUniqueId())) {
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
        String type = prizes.remove(w);
        ((HostedFFAWinner) w).awardPrize(type);
        return true;
    }
}
