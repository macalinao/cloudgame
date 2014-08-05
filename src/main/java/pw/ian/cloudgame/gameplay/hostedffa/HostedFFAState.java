/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import pw.ian.cloudgame.gameplay.FFAState;

/**
 *
 * @author ian
 */
public class HostedFFAState extends FFAState {

    private long start = -1;

    private boolean over = false;

    private int mins;

    private boolean provideArmor;

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

    public void setProvideArmor(boolean provideArmor) {
        this.provideArmor = provideArmor;
    }

    public boolean isProvideArmor() {
        return provideArmor;
    }

}
