package pw.ian.cloudgame.gameplay.core;

import pw.ian.cloudgame.gameplay.State;
import pw.ian.cloudgame.gameplay.Winner;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * A team winner of a game
 *
 * @author Ollie
 */
public class TeamWinner<T extends State> implements Winner<T> {
    private final List<UUID> players;

    public TeamWinner(List<UUID> players) {
        this.players = new ArrayList<>(players);
    }

    public TeamWinner(UUID... uuids) {
        players = Arrays.asList(uuids);
    }

    public TeamWinner(Player... players) {
        List<UUID> uuids = new ArrayList<>();
        for (Player player : players) {
            uuids.add(player.getUniqueId());
        }
        this.players = uuids;
    }

    public List<UUID> getPlayerIds() {
        return new ArrayList<UUID>(players);
    }

    public List<Player> getPlayers() {
        List<Player> result = new ArrayList<Player>();
        for (UUID uuid : players) {
            result.add(Bukkit.getPlayer(uuid));
        }
        return result;
    }

    @Override
    public void sendMessage(String message) {
        for (UUID id : players) {
            Bukkit.getPlayer(id).sendMessage(message);
        }
    }
}
