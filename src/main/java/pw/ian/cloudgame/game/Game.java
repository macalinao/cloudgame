/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.events.GameStopEvent;
import pw.ian.cloudgame.gameplay.GameMaster;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.State;
import pw.ian.cloudgame.model.arena.Arena;
import pw.ian.cloudgame.stats.Stats;

/**
 *
 * @author ian
 * @param <T> The type of state of this game
 */
public class Game<T extends State> {

    private final Gameplay<T> gameplay;

    private final Arena arena;

    private final GameMaster master;

    private final T state;

    private final Stats stats;

    Game(Gameplay<T> gameplay, Arena arena, GameMaster master) {
        this.gameplay = gameplay;
        this.arena = arena;
        this.master = master;
        state = gameplay.newState();
        stats = new Stats();
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public Arena getArena() {
        return arena;
    }

    public T getState() {
        return state;
    }

    public Stats getStats() {
        return stats;
    }

    public GameMaster getGameMaster() {
        return master;
    }

    /**
     * Broadcasts a message to all players in the game.
     *
     * @param message
     */
    public void broadcast(String message) {
        for (Player player : state.getParticipants()) {
            gameplay.sendGameMessage(player, message);
        }
    }

    /**
     * Stops this game.
     */
    public void stop() {
        Bukkit.getPluginManager().callEvent(new GameStopEvent(this));
    }

}
