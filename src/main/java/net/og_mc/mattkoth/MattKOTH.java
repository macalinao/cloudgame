/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import net.og_mc.mattkoth.tasks.KOTHAnnouncerTask;
import net.og_mc.mattkoth.listeners.KOTHGameListener;
import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.model.arena.Arena;
import com.simplyian.cloudgame.model.region.Region;
import net.og_mc.mattkoth.listeners.KOTHCaptureListener;
import net.og_mc.mattkoth.listeners.KOTHCommandListener;

/**
 *
 * @author ian
 */
public class MattKOTH extends Gameplay<KOTHState> {

    private Game<KOTHState> game;

    public MattKOTH(CloudGame plugin) {
        super(plugin, "KOTH");
    }

    @Override
    public void onEnable() {
        getPlugin().getCommands().registerCommand("koth", new KOTHCommand(this));

        getPlugin().getServer().getPluginManager().registerEvents(new KOTHCaptureListener(this), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new KOTHCommandListener(this), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new KOTHGameListener(this), getPlugin());
    }

    public Game<KOTHState> getGame() {
        return game;
    }

    public void setGame(Game<KOTHState> game) {
        this.game = game;
    }

    @Override
    public boolean canUse(Arena arena) {
        String hillRegion = arena.getProperty("koth.hill").toString();
        Region region = getPlugin().getModelManager().getRegions().findById(hillRegion);
        return region != null;
    }

    @Override
    public void setup(Game<KOTHState> g) {
        (new KOTHAnnouncerTask(g)).runTaskTimer(getPlugin(), 2L, 2L);
    }
}
