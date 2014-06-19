/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth.listeners;

import com.simplyian.cloudgame.events.GameEndEvent;
import com.simplyian.cloudgame.events.GameStartEvent;
import com.simplyian.cloudgame.events.GameStopEvent;
import com.simplyian.cloudgame.game.Game;
import com.simplyian.cloudgame.gameplay.listeners.GameListener;
import net.og_mc.mattkoth.KOTHState;
import net.og_mc.mattkoth.tasks.KOTHTimer;
import net.og_mc.mattkoth.MattKOTH;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

/**
 *
 * @author ian
 */
public class KOTHGameListener extends GameListener<KOTHState> {

    public KOTHGameListener(MattKOTH koth) {
        super(koth);
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        KOTHState state = game.getState();
        for (Player p : state.getPlayers()) {
            Location spawn = game.getArena().getNextSpawn();
            p.teleport(spawn);
        }

        state.setStarted();
        (new KOTHTimer(game)).runTaskTimer(getGameplay().getPlugin(), 2L, 2L);
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        Player winner = game.getState().getCapturer();
        if (winner == null) {
            game.broadcast("Game over! Nobody won!");
        } else {
            game.broadcast(ChatColor.YELLOW + winner.getName() + ChatColor.RED + " has won the KOTH!");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ccrates give 3 " + winner.getName() + " 3");
        }

        game.stop();
    }

    @EventHandler
    public void onGameStop(GameStopEvent event) {
        Game<KOTHState> game = game(event);
        if (game == null) {
            return;
        }

        for (Player player : game.getState().getSpectators()) {
            getGameplay().getPlugin().getPlayerStateManager().loadState(player);
        }
        getGameplay().getPlugin().getGameManager().removeGame(game);
        ((MattKOTH) getGameplay()).setGame(null);

    }

}
