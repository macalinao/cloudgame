/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay.hostedffa;

import com.simplyian.cloudgame.gameplay.FFAState;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class HostedFFAState extends FFAState {

    private UUID host;

    private long start = -1;

    private boolean over = false;

    private int mins;

    private boolean easy;

    public Player getHost() {
        return Bukkit.getPlayer(host);
    }

    public void setHost(Player player) {
        host = player.getUniqueId();
    }

    public boolean isStarted() {
        return start != -1;
    }

    public void setStarted() {
        this.start = System.currentTimeMillis();
    }

    public long getStart() {
        return start;
    }

    public int remainingTime() {
        int secsElapsed = (((int) (System.currentTimeMillis() - start)) / 1000);
        return (mins * 60) - secsElapsed;
    }

    @Override
    public List<Player> getParticipants() {
        List<Player> participants = new ArrayList<>();
        participants.addAll(getSpectators());
        participants.addAll(getPlayers());
        Player host = getHost();
        if (host != null) {
            participants.add(host);
        }
        return participants;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver() {
        this.over = true;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public void setEasy(boolean easy) {
        this.easy = easy;
    }

    public boolean isEasy() {
        return easy;
    }

}
