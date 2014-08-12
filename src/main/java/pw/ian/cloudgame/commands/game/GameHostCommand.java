package pw.ian.cloudgame.commands.game;

import pw.ian.albkit.command.parser.Arguments;
import pw.ian.cloudgame.CloudGame;
import pw.ian.cloudgame.game.Game;
import pw.ian.cloudgame.gameplay.Gameplay;
import pw.ian.cloudgame.hosted.PlayerHost;
import pw.ian.cloudgame.model.arena.Arena;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.ian.albkit.command.CommandHandler;
import pw.ian.cloudgame.hosted.ConsoleHost;
import pw.ian.cloudgame.hosted.Host;
import pw.ian.cloudgame.hosted.HostedGameCountdown;

public class GameHostCommand extends CommandHandler {

    private final CloudGame cloudGame;

    public GameHostCommand() {
        super("host");
        this.cloudGame = CloudGame.inst();

        setDescription("Hosts a new game of the given type");
        setUsage("/game host <gameplay> <arena> [time]");
        setPermission("cloudgame.admin");
    }

    @Override
    public void onCommand(CommandSender sender, Arguments args) {
        if (args.length() < 3) {
            sendUsageMessage(sender);
            return;
        }

        Gameplay gameplay = cloudGame.getGameplayManager().getGameplay(args.getRaw(1));
        if (gameplay == null) {
            sender.sendMessage(ChatColor.RED + "That game type doesn't exist!");
            return;
        }

        Arena arena = cloudGame.getModelManager().getArenas().find(args.getRaw(2));
        if (arena == null) {
            gameplay.sendGameMessage(sender, "That arena doesn't exist!");
            return;
        }

        Host host = (sender instanceof Player) ? new PlayerHost((Player) sender) : ConsoleHost.INSTANCE;
        Game game = cloudGame.getGameManager().createGame(gameplay, arena, host);
        if (game == null) {
            gameplay.sendGameMessage(sender, "Failed to create game!");
            return;
        }

        // TODO: How do we handle gameplay-specific stuff like easy/hard & time
        (new HostedGameCountdown(game)).runTaskTimer(cloudGame, 2, 2);
        gameplay.sendGameMessage(sender, "Scheduled game to start!");
    }
}
