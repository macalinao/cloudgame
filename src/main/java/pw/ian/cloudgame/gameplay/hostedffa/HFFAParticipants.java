/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.gameplay.hostedffa;

import pw.ian.cloudgame.gameplay.FFAParticipants;

/**
 *
 * @author ian
 */
public class HFFAParticipants extends FFAParticipants {

    private boolean provideArmor;

    public void setProvideArmor(boolean provideArmor) {
        this.provideArmor = provideArmor;
    }

    public boolean isProvideArmor() {
        return provideArmor;
    }

}
