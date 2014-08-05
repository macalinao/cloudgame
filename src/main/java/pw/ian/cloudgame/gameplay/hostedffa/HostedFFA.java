/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Winner;
import pw.ian.cloudgame.mixins.BasicFlowControl;
import pw.ian.cloudgame.mixins.NoCommands;
import pw.ian.cloudgame.mixins.QuitOnDeath;
import pw.ian.cloudgame.mixins.TransientInventories;
import pw.ian.cloudgame.mixins.TimeLimit;

/**
 *
 * @author ian
 */
public abstract class HostedFFA extends Gameplay {

    private final Map<Winner, String> prizes = new HashMap<>();

    protected HostedFFA(CloudGame plugin, String name) {
        super(plugin, name);
    }

    @Override
    public void onEnable() {
        mixin(BasicFlowControl.class);
        mixin(TimeLimit.class);
        mixin(NoCommands.class);
        mixin(QuitOnDeath.class);
        mixin(TransientInventories.class);
//        getPlugin().getServer().getPluginManager().registerEvents(new FFAGameListener(this), getPlugin());
//        getPlugin().getServer().getPluginManager().registerEvents(new FFAGamePlayerListener(this), getPlugin());
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
