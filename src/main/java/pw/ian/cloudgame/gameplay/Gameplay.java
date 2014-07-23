/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay;

import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.model.arena.Arena;
import pw.ian.cloudgame.util.Messaging;

import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Contains all game gameplay.
 *
 * @author ian
 * @param <T> The type of state associated with this game.
 */
public abstract class Gameplay<T extends State> {

    private final CloudGame plugin;

    private final String id;

    private final String name;

    private ColorScheme colorScheme = ColorScheme.DEFAULT;

    protected Gameplay(CloudGame plugin, String id) {
        this.plugin = plugin;
        this.id = id.toLowerCase();
        this.name = id.toUpperCase();
    }

    public CloudGame getPlugin() {
        return plugin;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    /**
     * Called when the plugin is enabled. Set up listeners etc here.
     */
    public void onEnable() {
    }

    /**
     * Called when the plugin is disabled.
     */
    public void onDisable() {
    }

    /**
     * Creates a new state associated with this game gameplay.
     *
     * @return
     */
    public T newState() {
        try {
            return (T) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Sends a game related message to the given player.
     *
     * @param p
     * @param message
     */
    public void sendGameMessage(Player p, String message) {
        p.sendMessage(colorScheme.getPrefix() + "[" + name + "] " + colorScheme.getMsg() + colorScheme.replaceColors(message));
    }

    /**
     * Sends a game related message to all the players given via the Winner
     * object.
     *
     * @param w
     * @param message
     */
    public void sendGameMessage(Winner<? extends State> w, String message) {
        w.sendMessage(colorScheme.getPrefix() + "[" + name + "]" + colorScheme.getMsg() + colorScheme.replaceColors(message));
    }

    /**
     * Sends a banner to the given commandsender.
     *
     * @param sender
     * @param message
     */
    public void sendBanner(CommandSender sender, Object... message) {
        Messaging.sendBanner(colorScheme, sender, message);
    }

    /**
     * Returns true if this Gameplay can use the given arena.
     *
     * @param arena
     * @return
     */
    public boolean canUse(Arena arena) {
        return !arena.getSpawns().isEmpty();
    }

    /**
     * Sets up the gameplay.
     *
     * @param g
     */
    public abstract void setup(Game<T> g);

}
