/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author ian
 */
public class Messaging {

    private Messaging() {
    }

    public static void sendBanner(Player p, String... message) {
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "-" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "--------------------------------------------------" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "-");
        for (String line : message) {
            p.sendMessage(ChatColor.GREEN + line);
        }
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "-" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "--------------------------------------------------" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "-");
    }
}
