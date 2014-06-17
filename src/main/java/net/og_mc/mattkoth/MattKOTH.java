/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.CloudGame;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.Gameplay;
import com.simplyian.cloudgame.model.arena.Arena;
import com.simplyian.cloudgame.model.region.Region;
import java.util.ArrayList;
import java.util.List;
import net.og_mc.mattkoth.handlers.JoinHandler;
import net.og_mc.mattkoth.handlers.LeaveHandler;
import net.og_mc.mattkoth.handlers.QuitHandler;
import net.og_mc.mattkoth.handlers.StartHandler;
import org.bukkit.entity.Player;

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
        getPlugin().getCommand("koth").setExecutor(new KOTHCommand(this));

        addHandler("START", new StartHandler(getPlugin()));
        addHandler("JOIN", new JoinHandler(getPlugin()));
        addHandler("LEAVE", new LeaveHandler(getPlugin()));
        addHandler("QUIT", new QuitHandler(getPlugin()));
    }

    public boolean createGame(Arena arena) {
        if (game != null) {
            return false;
        }
        game = getPlugin().getGameManager().createGame(this, arena);
        return true;
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

    @Override
    public List<Player> getPlayers(Game<KOTHState> game) {
        return new ArrayList<>(game.getState().getPlayers());
    }
}
