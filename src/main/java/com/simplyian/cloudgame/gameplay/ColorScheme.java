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

    public static final ColorScheme DEFAULT = new ColorScheme(ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.DARK_RED, ChatColor.WHITE, ChatColor.YELLOW);

    private final ChatColor light;

    private final ChatColor dark;

    private final ChatColor prefix;

    private final ChatColor msg;

    private final ChatColor highlight;

    public ColorScheme(ChatColor light, ChatColor dark, ChatColor prefix, ChatColor msg, ChatColor highlight) {
        this.light = light;
        this.dark = dark;
        this.prefix = prefix;
        this.msg = msg;
        this.highlight = highlight;
    }

    public ChatColor getLight() {
        return light;
    }

    public ChatColor getDark() {
        return dark;
    }

    public ChatColor getPrefix() {
        return prefix;
    }

    public ChatColor getMsg() {
        return msg;
    }

    public ChatColor getHighlight() {
        return highlight;
    }

    public String replaceColors(String msg) {
        return msg
                .replaceAll("\\$D", dark.toString()).replaceAll("\\$L", light.toString())
                .replaceAll("\\$M", this.msg.toString()).replaceAll("\\$H", highlight.toString())
                .replaceAll("\\$P", prefix.toString());
    }

}
