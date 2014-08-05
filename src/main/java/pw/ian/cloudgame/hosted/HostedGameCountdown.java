/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.hosted;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.ian.albkit.util.Countdown;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.State;

/**
 * Announces the game and starts it when the time is up.
 *
 * @param <T>
 */
public class HostedGameCountdown<T extends State> extends Countdown {

    private static final Map<Integer, String> messages = new HashMap<>();

    static {
        messages.put(10, "10 seconds");
        messages.put(30, "30 seconds");
        messages.put(1 * 60, "1 minute");
        messages.put(3 * 60, "3 minutes");
        messages.put(5 * 60, "5 minutes");
    }

    private final Game<T> game;

    public HostedGameCountdown(Game<T> game) {
        super(messages);
        this.game = game;
    }

    @Override
    public boolean checkCondition() {
        return !(game.getState().isStarted() || game.getState().isOver());
    }

    @Override
    public void onCheckpoint(int seconds, String time) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            game.getGameplay().sendBanner(p,
                    "A " + game.getGameplay().getName() + " on map $D" + game.getArena().getName() + " $Lis starting in $D" + time + "$L!",
                    "Type $D/" + game.getGameplay().getId() + " join $Lto join $D"
                    + game.getState().getPlayers().size() + " $Lother players!");
        }
    }

    @Override
    public void onEnd() {
        game.events().start();
    }

    public void runTimer() {
        runTaskTimer(CloudGame.inst(), 2, 2);
    }

}
