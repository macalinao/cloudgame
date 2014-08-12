/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.gameplay.Mixin;
import pw.ian.cloudgame.states.RemainingTime;

/**
 *
 * @author ian
 */
public class TimeLimit extends Mixin {

    public TimeLimit(Gameplay gameplay) {
        super(gameplay);
    }

    @Override
    public void setup() {
        state(RemainingTime.class);
    }

}
