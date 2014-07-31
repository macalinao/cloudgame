/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.events.GameEventFactory;
import pw.ian.cloudgame.events.GameStartEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Timer;

/**
 * Announces the game and starts it when the time is up.
 *
 * @param <T>
 */
public class HostedFFAAnnouncerTask<T extends HostedFFAState> extends Timer<T> {

    private static final Map<Integer, String> messages = new HashMap<>();

    static {
        messages.put(10, "10 seconds");
        messages.put(30, "30 seconds");
        messages.put(1 * 60, "1 minute");
        messages.put(3 * 60, "3 minutes");
        messages.put(5 * 60, "5 minutes");
    }

    private final Game<T> game;

    public HostedFFAAnnouncerTask(Game<T> game) {
        super(game, messages);
        this.game = game;
    }

    @Override
    public void onCheckpoint(int seconds, String time) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            game.getGameplay().sendBanner(p,
                    "A " + game.getGameplay().getName() + " on map $D" + game.getArena().getName() + " $Lis starting in $D" + time + "$L!",
                    "Type $D/" + game.getGameplay().getId() + " join $Lto join $D"
                    + game.getState().getPlayers().size() + " $Lother players! "
                    + (game.getState().isProvideArmor() ? "(armor provided)" : ChatColor.RED + "(armor not provided)"));
        }
    }

    @Override
    public void onEnd() {
        GameEventFactory.callGameStartEvent(game);
    }

}
