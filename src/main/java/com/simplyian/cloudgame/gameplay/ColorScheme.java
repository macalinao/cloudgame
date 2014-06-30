/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.gameplay;

import org.bukkit.ChatColor;

/**
 *
 * @author ian
 */
public class ColorScheme {

    private final ChatColor light;

    private final ChatColor dark;

    public ColorScheme(ChatColor light, ChatColor dark) {
        this.light = light;
        this.dark = dark;
    }

    public ChatColor getLight() {
        return light;
    }

    public ChatColor getDark() {
        return dark;
    }

}
