package pw.ian.cloudgame.gameplay;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.CommandSender;
import pw.ian.albkit.util.ColorScheme;
import pw.ian.albkit.util.Messaging;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.model.arena.Arena;

/**
 * Contains all game gameplay.
 *
 * @author ian
 */
public abstract class Gameplay {

    private final CloudGame plugin;

    private final String id;

    private final String name;

    private String description = "Default description.";

    private ColorScheme colorScheme = ColorScheme.DEFAULT;

    /**
     * Stores the mixins associated with this Gameplay.
     */
    private LinkedHashMap<Class<? extends Mixin>, Mixin> mixins = new LinkedHashMap<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
     * Sends a game related message to the given CommandSender.
     *
     * @param sender
     * @param message
     */
    public void sendGameMessage(CommandSender sender, String message) {
        sender.sendMessage(colorScheme.getPrefix() + "[" + name + "] " + colorScheme.getMsg() + colorScheme.replaceColors(message));
    }

    /**
     * Sends a game related message to all the players given via the Winner
     * object.
     *
     * @param w
     * @param message
     */
    public void sendGameMessage(Winner w, String message) {
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
     * Applies this Gameplay's mixins to the given game.
     *
     * @param game The game
     */
    public void applyMixins(Game game) {
        for (Mixin m : mixins.values()) {
            m.applyStates(game);
        }
    }

    /**
     * Sets up the gameplay.
     *
     * @param g
     */
    public void setup(Game g) {
    }

    /**
     * Applies a mixin to this Gameplay.
     *
     * @param clazz
     */
    protected void mixin(Class<? extends Mixin> clazz) {
        try {
            Mixin m = (Mixin) clazz.getConstructors()[0].newInstance(this); // will be reworked when we remove generics
            m.resolveDependencies();
            m.setup();
            mixins.put(clazz, m);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            CloudGame.inst().getLogger().log(Level.SEVERE, "Could not instantiate mixin instance!", ex);
        }
    }

    /**
     * Checks if this Gameplay possesses the given mixin.
     *
     * @param clazz The class of the mixin.
     * @return Boolean
     */
    public boolean hasMixin(Class<? extends Mixin> clazz) {
        return mixins.containsKey(clazz);
    }
}
