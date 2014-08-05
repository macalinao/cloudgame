/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay;

import java.util.List;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public interface Participants {

    public boolean isStarted();

    public boolean isOver();

    public List<Player> getPlayers();

    public List<Player> getSpectators();

    public void removePlayer(Player p);

    public boolean hasPlayer(Player p);

    public boolean hasSpectator(Player p);

    public void addSpectator(Player p);

    public void removeSpectator(Player p);

    public List<Player> getParticipants();

}
