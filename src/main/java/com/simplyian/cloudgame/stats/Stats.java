/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.stats;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ian
 */
public class Stats {

    private Set<Death> deaths = new HashSet<>();

    public Stats() {
    }

    public void addDeath(Death death) {
        deaths.add(death);
    }

}
