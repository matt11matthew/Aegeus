package aegeus.com.aegeus.commands;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import aegeus.com.aegeus.methods.ChatMethods;
import aegeus.com.aegeus.util.Helper;

public class CommandRoll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int max = 100;
		if (args.length >= 1) {
			try { max = Integer.parseInt(args[0]); }
			catch (NumberFormatException e) { return false; }
		}
		
		Random random = new Random();
		int roll = random.nextInt(max);
		sender.sendMessage(Helper.colorCodes(
				"&7You rolled a &f" + roll + "&7 out of &f" + max + "&7."));
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			ChatMethods.sendRadialChat(player, ChatColor.GRAY + player.getDisplayName() + " rolled a " + ChatColor.WHITE + roll
					+ ChatColor.GRAY + " out of " + ChatColor.WHITE + max + ChatColor.GRAY + ".");
		}
		return true;
	}

}
