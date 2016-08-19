package aegeus.com.aegeus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import aegeus.com.aegeus.methods.ChatMethods;
import aegeus.com.aegeus.obj.AegeusArmor;
import aegeus.com.aegeus.obj.AegeusWeapon;
import aegeus.com.aegeus.types.ChatChannel;
import aegeus.com.aegeus.types.ItemRarity;
import aegeus.com.aegeus.util.Helper;
import aegeus.com.aegeus.util.InventorytoBase64;

public class App extends JavaPlugin {
	
	public static final String build = "dev";
	public static final String buildNote = "Testing build.";
	
	@Override
	public void onEnable() {
		getDataFolder().mkdir();
		getLogger().log(Level.INFO, "Aegeus enabling...");
		try {
			getServer().getPluginManager().registerEvents(new Server(this), this);
			getServer().getPluginManager().registerEvents(new Combat(this), this);
			getServer().getPluginManager().registerEvents(new Chat(this), this);
			getServer().getPluginManager().registerEvents(new Mobs(this), this);
			getServer().getPluginManager().registerEvents(new Mining(this), this);
			getServer().getPluginManager().registerEvents(new Statistics(this), this);
			getLogger().log(Level.INFO, "Aegeus enabled.");
		} catch (Exception e) {
			getLogger().log(Level.SEVERE, "Aegeus failed to load.", e);
		}
	}

	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, "Aegeus disabled.");
	}

	private String buildArgString(String[] args, int start) {
		List<String> build = new ArrayList<>();
		for(int i = start; i < args.length; i++){
			build.add(args[i]);
		}
		return StringUtils.join(build, " ");
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("global")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
				return true;
			}
			if (args.length < 1) return false;
			final Player player = (Player) sender;
			ChatMethods.sendAutoChat(player, Helper.colorCodes(buildArgString(args, 0)));
			return true;
		} else if (cmd.getName().equalsIgnoreCase("roll")) {
			int max = 100;
			if (args.length >= 1) {
				try {
					max = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					return false;
				}
			}
			Random random = new Random();
			int roll = random.nextInt(max);
			sender.sendMessage(ChatColor.GRAY + "You rolled a " + ChatColor.WHITE + roll + ChatColor.GRAY + " out of "
					+ ChatColor.WHITE + max + ChatColor.GRAY + ".");
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ChatMethods.sendRadialChat(player, ChatColor.GRAY + player.getDisplayName() + " rolled a " + ChatColor.WHITE + roll
						+ ChatColor.GRAY + " out of " + ChatColor.WHITE + max + ChatColor.GRAY + ".");
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("testweapon") && sender instanceof Player && sender.isOp()) {
			Player player = (Player) sender;
			AegeusWeapon wep = new AegeusWeapon(Material.DIAMOND_AXE);
			wep.setName("&dMordebit Axe of Ice Fire");
			wep.setDmg(165, 213);
			wep.setFireDmg(18);
			wep.setIceDmg(12);
			wep.setLifeSteal(9);
			wep.setTier(4);
			wep.setLevel(1);
			wep.setRarity(ItemRarity.RARE);
			player.getInventory().addItem(wep.build());
			return true;
		} else if (cmd.getName().equalsIgnoreCase("testarmor") && sender instanceof Player && sender.isOp()) {
			Player player = (Player) sender;
			AegeusArmor armor = new AegeusArmor(Material.CHAINMAIL_LEGGINGS);
			armor.setName("&dAgile Chain Leggings");
			armor.setHp(245);
			armor.setEnergyRegen(4);
			armor.setTier(2);
			armor.setRarity(ItemRarity.UNIQUE);
			player.getInventory().addItem(armor.build());
			return true;
		} else if (cmd.getName().equalsIgnoreCase("message")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
				return true;
			}
			if(args.length < 1)
				return false;
			Player p = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
			args[0] = " ";
			String message = "";
			for (String i : args) {
				message += i + " ";
			}
			ChatMethods.sendPrivateMessage(p, target, message.trim());
		} else if (cmd.getName().equals("chatchannel") && sender instanceof Player) {
			if(args.length < 1){
				return false;
			}
			if (args[0].equals("l") || args[0].equals("local")) {
				Chat.playerChatChannel.put((Player) sender, ChatChannel.LOCAL);
				sender.sendMessage("" + ChatColor.GRAY + ChatColor.ITALIC + "Chat Channel set to Local.");
			} else if (args[0].equals("gl") || args[0].equals("global")) {
				Chat.playerChatChannel.put((Player) sender, ChatChannel.GLOBAL);
				sender.sendMessage("" + ChatColor.GRAY + ChatColor.ITALIC + "Chat Channel set to Global.");
			} else {
				sender.sendMessage(ChatColor.RED + "Invalid arguments!");
			}
			return true;
		}
		return false;
	}
}
