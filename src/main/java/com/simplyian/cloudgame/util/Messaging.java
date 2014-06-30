/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplyian.cloudgame.util;

import com.simplyian.cloudgame.gameplay.ColorScheme;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author ian
 */
public class Messaging {

    private Messaging() {
    }

    public static void sendBanner(ColorScheme scheme, CommandSender sender, Object... message) {
        sender.sendMessage(ChatColor.GRAY + " " + ChatColor.STRIKETHROUGH + "-" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "--------------------------------------------------" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "-");
        for (Object line : message) {
            sender.sendMessage(scheme.getLight() + " " + scheme.replaceColors(line.toString()));
        }
        sender.sendMessage(ChatColor.GRAY + " " + ChatColor.STRIKETHROUGH + "-" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "--------------------------------------------------" + ChatColor.GRAY + ChatColor.STRIKETHROUGH + "-");
    }
}
