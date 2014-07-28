/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.ian.cloudgame.commands.hostedffa;

import org.bukkit.entity.Player;
import pw.ian.albkit.command.PlayerCommandHandler;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFA;
import pw.ian.cloudgame.gameplay.hostedffa.HostedFFAState;

/**
 *
 * @author ian
 */
public class FFARedeemCommand extends PlayerCommandHandler {

    private final HostedFFA ffa;

    public FFARedeemCommand(HostedFFA ffa) {
        super("redeem");
        this.ffa = ffa;
        setDescription("Redeems a " + ffa.getName() + " prize.");
        setUsage("/" + ffa.getId() + " redeem");
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (ffa.getGame() != null && ((Game<HostedFFAState>) ffa.getGame()).getState().hasPlayer(player)) {
            ffa.sendGameMessage(player, "You can't use this command in game!");
            return;
        }

        if (!ffa.redeemPrize(player)) {
            ffa.sendGameMessage(player, "You don't have a prize to redeem!");
            return;
        }

        ffa.sendGameMessage(player, "Enjoy your prize!");
    }

}
