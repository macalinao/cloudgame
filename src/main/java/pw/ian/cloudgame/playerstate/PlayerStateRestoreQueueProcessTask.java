/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.playerstate;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * Restores {@link PlayerState}s after a game
 *
 * @author ian
 */
public class PlayerStateRestoreQueueProcessTask extends BukkitRunnable {

    private final PlayerStateManager psm; // peam solo mid

    public PlayerStateRestoreQueueProcessTask(PlayerStateManager psm) {
        this.psm = psm;
    }

    @Override
    public void run() {
        psm.processStateRestoreQueue();
    }

}
