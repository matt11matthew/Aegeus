package com.aegeus.aegeus;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.aegeus.aegeus.commands.CommandGlobal;
import com.aegeus.aegeus.commands.CommandMessage;
import com.aegeus.aegeus.commands.CommandRoll;
import com.aegeus.aegeus.commands.CommandTestArmor;
import com.aegeus.aegeus.commands.CommandTestWeapon;

public class Aegeus extends JavaPlugin {
	
	public static final String build = "dev";
	public static final String buildNote = "ASCII boats.";
	private static final Logger log = Logger.getLogger(Aegeus.class.getName());
	
	/**
	 * 
	 * Created by oopsjpeg, Silvre, and matt11matthew.
	 * There's probably one or two boats in this project.
	 * M 8, D 19, Y 2016
	 * 
	 */
	@Override
	public void onEnable() {	
		// wooOOOOOOOOO, loading up!
		log.log(Level.INFO, "Aegeus enabling...");
		saveDefaultConfig();
		
		// Register plugin events
		// TODO Clean up, move to organized packages
		log.log(Level.INFO, "Registering events...");
		getServer().getPluginManager().registerEvents(new Server(this), this);
		getServer().getPluginManager().registerEvents(new Combat(this), this);
		getServer().getPluginManager().registerEvents(new Chat(this), this);
		getServer().getPluginManager().registerEvents(new Mobs(this), this);
		getServer().getPluginManager().registerEvents(new Mining(this), this);
		getServer().getPluginManager().registerEvents(new Statistics(this), this);
		getServer().getPluginManager().registerEvents(new Bank(this), this);
		
		// Register plugin commands
		log.log(Level.INFO, "Registering commands...");
		getCommand("global").setExecutor(new CommandGlobal());
		getCommand("roll").setExecutor(new CommandRoll());
		getCommand("message").setExecutor(new CommandMessage());
		getCommand("roll").setExecutor(new CommandRoll());
		getCommand("testarmor").setExecutor(new CommandTestArmor());
		getCommand("testweapon").setExecutor(new CommandTestWeapon());
		
		// Done, done, and done!
		getLogger().log(Level.INFO, "Aegeus enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, "Aegeus disabled.");
	}
}
