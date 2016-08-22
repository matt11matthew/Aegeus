package com.aegeus.aegeus.game.planets;

import org.bukkit.Location;

public interface Planet {
	// TODO Planets package is dirty :/
	public String getID();
	public String getName();
	public String getDescription();
	public Location getLocation();
}
