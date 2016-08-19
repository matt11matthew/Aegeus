package aegeus.com.aegeus;

import java.util.HashMap;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import aegeus.com.aegeus.obj.AegeusPlayer;
import aegeus.com.aegeus.types.ChatChannel;
import aegeus.com.aegeus.util.Helper;
import net.md_5.bungee.api.ChatColor;

public class Server implements Listener {

	private JavaPlugin parent;

	public Server(JavaPlugin parent) {
		this.parent = parent;
//		Runnable checkBySecond = new Runnable() {
//			public void run() {
//				if (buffLootTime > 0) {
//					buffLootTime -= 1;
//					if (buffLootTime <= 0) {
//						Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + ">>" + ChatColor.GOLD
//								+ " Global Loot Buff EXPIRED");
//					}
//				}
//				if (rebootTime > 1) {
//					rebootTime -= 1;
//					if (rebootTime == 1200) {
//						Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>" + ChatColor.GOLD
//								+ " The server will reboot in 20 minutes.");
//					}
//					if (rebootTime == 600) {
//						Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>" + ChatColor.GOLD
//								+ " The server will reboot in 10 minutes.");
//					}
//					if (rebootTime == 300) {
//						Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>" + ChatColor.GOLD
//								+ " The server will reboot in 5 minutes.");
//					}
//					if (rebootTime == 60) {
//						Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + ">>" + ChatColor.GOLD
//								+ " The server will reboot in 1 minute. Use " + ChatColor.BOLD + "/logout"
//								+ ChatColor.GOLD + " to ensure your data is saved.");
//					}
//					if (rebootTime <= 5) {
//						Bukkit.broadcastMessage(
//								ChatColor.RED + "" + ChatColor.BOLD + ">>" + ChatColor.GOLD + " " + rebootTime + "...");
//					}
//					if (rebootTime <= 0) {
//						Bukkit.setWhitelist(true);
//						for (Player p : Bukkit.getOnlinePlayers()) {
//							p.sendMessage(
//									ChatColor.YELLOW + "Attempting to save player data. You should've logged out!");
//						}
//						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spigot:restart");
//					}
//				}
//			}
//		};
//		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//		executor.scheduleAtFixedRate(checkBySecond, 0, 1, TimeUnit.SECONDS);
	}

	public static int buffLootTime = 0;
	public static int rebootTime = 7200;

	public static HashMap<Player, Boolean> playerCombatLog = new HashMap<>();

	private String[] motds = { // List of MOTDs
			"The silent return!", "Back in business!", "You won't believe it!", "A BUG HERE AND A BUG THERE",
			"Still testing code!", "Easy game, easy life!", "The dungeons are everywhere!", "Too many daggers!",
			"My sword is unbelievably dull.", "Spooky scary skeletons!", "Kilvre silled it!", "Silvre killed it!",
			"What a mistake you've made.", "Pocket full of spells!", "Edurardo Elrico was here.", "Fix it, Crafty!",
			"WHO R U", ChatColor.GOLD + "This text isn't gray. Cool!", ChatColor.MAGIC + "You can't read this!",
			"All hail LeLouch!", "Tiers, loads of em'!", "Hey, what's for dinner?", "Cup noodles!",
			"Sometimes, I hug my anvil.", "a SKELEton." };

	@EventHandler
	private void onLoginEvent(PlayerLoginEvent event) {
		if (rebootTime <= 300) {
			event.setResult(Result.KICK_OTHER);
			event.setKickMessage("The server is about to reboot.");
		}
	}

	@EventHandler
	private void onCommandEvent(ServerCommandEvent event) {
		Bukkit.broadcast(ChatColor.DARK_GRAY + event.getSender().getName() + " executed " + event.getCommand(), "*");
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	// Login messages and initial player setup
	// TODO clean this
	private void onJoinEvent(PlayerJoinEvent event) throws InterruptedException {
		Chat.playerChatChannel.put(event.getPlayer(), ChatChannel.LOCAL);
		Player player = event.getPlayer();
		// TODO load player's data from file
		Statistics.playerData.put(player, new AegeusPlayer());
		event.setJoinMessage("");
		player.setHealthScaled(true);
		player.setHealthScale(20);
		if (!player.hasPlayedBefore()) {
			player.sendTitle("", Helper.colorCodes("&aWelcome."));
		} else {
			player.sendTitle("", Helper.colorCodes("&fWelcome back."));
		}
		for (int i = 0; i < 10; i++) {
			player.sendMessage(" ");
		}
		player.sendMessage(Helper.colorCodes(
				"        &aAegeus &f&lMMORPG&f\n" +
				"        &a» &7Build &f" + Aegeus.build + " &7(&o" + Aegeus.buildNote + "&7)\n" +
				"        &7Modify game settings with &a/settings"));
		for (int i = 0; i < 3; i++) {
			player.sendMessage(" ");
		}
		Statistics.updateStats(player);
	}

	@EventHandler
	// Clear user information and punish combat loggers
	private void onLogoutEvent(PlayerQuitEvent event) {
		event.setQuitMessage("");
		Statistics.playerData.remove(event.getPlayer());
	}

	@EventHandler
	// Random, custom MOTDs
	private void onServerListPingEvent(ServerListPingEvent event) {
		Random random = new Random();
		if(Bukkit.hasWhitelist()){
			event.setMotd(ChatColor.GREEN + "Aegeus " + ChatColor.WHITE + ChatColor.BOLD + "MMORPG\n" + ChatColor.GRAY
					+ ChatColor.RED + "Undergoing Maintenance");
		} else {
			event.setMotd(ChatColor.GREEN + "Aegeus " + ChatColor.WHITE + ChatColor.BOLD + "MMORPG\n" + ChatColor.GRAY
					+ motds[random.nextInt(motds.length)]);
		}
	}

	@EventHandler
	// Disable crafting
	private void onCraftEvent(CraftItemEvent event) {
		event.setCancelled(true);
	}
}
