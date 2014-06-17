/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.og_mc.mattkoth;

import com.simplyian.cloudgame.gameplay.states.FFAState;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class KOTHState extends FFAState {

    private boolean started = false;

    private UUID capturer;

    private long captureStart = -1;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Player getCapturer() {
        return Bukkit.getPlayer(capturer);
    }

    public void setCapturer(Player capturer) {
        if (capturer == null) {
            this.capturer = null;
            this.captureStart = -1;
            return;
        }

        this.capturer = capturer.getUniqueId();
        this.captureStart = System.currentTimeMillis();
    }

    public int secondsCaptured() {
        if (captureStart == -1) {
            return 0;
        }
        return (int) ((System.currentTimeMillis() - captureStart) / 1000);
    }

}
