package com.aegeus.aegeus.game.planets;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Terminal implements Planet {
	
	private static final String ID = "terminal";
	private static final String name = "The Terminal";
	private static final String description = "The hub of global communication.";
	private static final Location location = Bukkit.getWorld(ID).getSpawnLocation();

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public Location getLocation() {
		return location;
	}

}
