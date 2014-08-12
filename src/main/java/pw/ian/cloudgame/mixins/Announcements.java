/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.mixins;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import pw.ian.cloudgame.events.GameJoinEvent;
import pw.ian.cloudgame.events.GameStartEvent;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.FFAParticipants;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.hosted.Host;
import pw.ian.cloudgame.mixin.Mixin;
import pw.ian.cloudgame.states.Status;

/**
 * Global announcements when the game starts/ends.
 */
public class Announcements extends Mixin {

    public Announcements(Gameplay gameplay) {
        super(gameplay);
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            getGameplay().sendBanner(p, "A " + getGameplay().getName() + " on map $D" + game.getArena().getName() + " $Lhas started!",
                    "Type $D/" + getGameplay().getId() + " spectate $Lto spectate it!");
        }
    }

    @EventHandler
    public void onGameJoin(GameJoinEvent event) {
        Game game = game(event);
        if (game == null) {
            return;
        }

        getGameplay().sendBanner(event.getPlayer(), "You've joined the " + getGameplay().getId() + "! Pay attention to the countdown.",
                "Want to leave the game? Type $D/" + getGameplay().getId() + " leave$L!");
    }
}
