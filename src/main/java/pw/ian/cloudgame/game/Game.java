/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.game;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.events.GameEventFactory;
import pw.ian.cloudgame.gameplay.FFAParticipants;
import pw.ian.cloudgame.gameplay.GameMaster;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Participants;
import pw.ian.cloudgame.gameplay.State;
import pw.ian.cloudgame.model.arena.Arena;
import pw.ian.cloudgame.stats.Stats;

/**
 *
 * @author ian
 * @param <T> The type of state of this game
 */
public class Game {

    private final Gameplay gameplay;

    private final Arena arena;

    private final GameMaster master;

    private final Participants participants;

    private final Stats stats;

    private final Map<Class<? extends State>, State> states = new HashMap<>();

    Game(Gameplay gameplay, Arena arena, GameMaster master) {
        this.gameplay = gameplay;
        this.arena = arena;
        this.master = master;
        participants = new FFAParticipants();
        stats = new Stats();
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public Arena getArena() {
        return arena;
    }

    public Participants getParticipants() {
        return participants;
    }

    public Stats getStats() {
        return stats;
    }

    public GameMaster getGameMaster() {
        return master;
    }

    public GameEventFactory events() {
        return new GameEventFactory(this);
    }

    /**
     * Broadcasts a message to all players in the game.
     *
     * @param message
     */
    public void broadcast(String message) {
        for (Player player : participants.getParticipants()) {
            gameplay.sendGameMessage(player, message);
        }
    }

    /**
     * Adds a state to this Gameplay and replaces one if it already exists.
     *
     * @param clazz
     */
    public State newState(Class<? extends State> clazz) {
        try {
            State state = clazz.newInstance();
            states.put(clazz, state);
            return state;
        } catch (InstantiationException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Gets a state associated with this Gameplay.
     *
     * @param <S>
     * @param clazz
     * @return Null if the state does not exist
     */
    public <S extends State> S state(Class<S> clazz) {
        return (S) states.get(clazz);
    }

}
