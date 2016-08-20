package aegeus.com.aegeus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import aegeus.com.aegeus.commands.CommandGlobal;
import aegeus.com.aegeus.commands.CommandMessage;
import aegeus.com.aegeus.commands.CommandRoll;
import aegeus.com.aegeus.commands.CommandTestArmor;
import aegeus.com.aegeus.commands.CommandTestWeapon;
import aegeus.com.aegeus.methods.ChatMethods;
import aegeus.com.aegeus.obj.AegeusArmor;
import aegeus.com.aegeus.obj.AegeusWeapon;
import aegeus.com.aegeus.types.ChatChannel;
import aegeus.com.aegeus.types.ItemRarity;
import aegeus.com.aegeus.util.Helper;

public class Aegeus extends JavaPlugin {

	public static final String build = "dev";
	public static final String buildNote = "Testing build.";



	@Override
	public void onEnable() {
		saveDefaultConfig();
		getLogger().log(Level.INFO, "Aegeus enabling...");

		// Register plugin events
		// TODO Clean up, move to organized packages
		getServer().getPluginManager().registerEvents(new Server(this), this);
		getServer().getPluginManager().registerEvents(new Combat(this), this);
		getServer().getPluginManager().registerEvents(new Chat(this), this);
		getServer().getPluginManager().registerEvents(new Mobs(this), this);
		getServer().getPluginManager().registerEvents(new Mining(this), this);
		getServer().getPluginManager().registerEvents(new Statistics(this), this);
		getServer().getPluginManager().registerEvents(new Bank(this), this);

		// Register plugin commands
		getCommand("global").setExecutor(new CommandGlobal());
		getCommand("roll").setExecutor(new CommandRoll());
		getCommand("message").setExecutor(new CommandMessage());
		getCommand("roll").setExecutor(new CommandRoll());
		getCommand("testarmor").setExecutor(new CommandTestArmor());
		getCommand("testweapon").setExecutor(new CommandTestWeapon());

		getLogger().log(Level.INFO, "Aegeus enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, "Aegeus disabled.");
	}
}
