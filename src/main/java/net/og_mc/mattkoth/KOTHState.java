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
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author ian
 */
public class KOTHState extends FFAState {

    private long start = -1;

    private UUID capturer;

    private long captureStart = -1;

    private ItemStack capturerHelmet;

    public boolean isStarted() {
        return start != -1;
    }

    public void setStarted() {
        this.start = System.currentTimeMillis();
    }

    public long getStart() {
        return start;
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
            return -1;
        }
        return (int) ((System.currentTimeMillis() - captureStart) / 1000);
    }

    public int remainingTime() {
        int secsElapsed = (((int) (System.currentTimeMillis() - start)) / 1000);
        return (10 * 60) - secsElapsed;
    }

    public ItemStack getCapturerHelmet() {
        return capturerHelmet;
    }

    public void setCapturerHelmet(ItemStack capturerHelmet) {
        this.capturerHelmet = capturerHelmet;
    }

}
