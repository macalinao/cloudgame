/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.playerstate;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author simplyianm
 */
public class PlayerState {

    private final float xp;

    private final ItemStack[] main;

    private final ItemStack[] armor;

    public PlayerState(float xp, ItemStack[] main, ItemStack[] armor) {
        this.xp = xp;
        this.main = main;
        this.armor = armor;
    }

    public PlayerState(Player p) {
        xp = p.getExp();
        main = p.getInventory().getContents();
        armor = p.getInventory().getArmorContents();
    }

    public void restore(Player p) {
        p.setExp(xp);
        p.getInventory().setContents(main);
        p.getInventory().setArmorContents(armor);
    }

    public float getXp() {
        return xp;
    }

    public ItemStack[] getMain() {
        return main;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

}
