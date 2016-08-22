package com.aegeus.aegeus.player;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bukkit.boss.BossBar;
import org.bukkit.inventory.Inventory;

import com.aegeus.aegeus.game.planets.Planet;
import com.aegeus.aegeus.game.planets.Planets;

public class PlayerData {
	
	public BossBar BossBarHP;
	public Planet CurrentPlanet = Planets.TERMINAL.getPlanet();
	private UUID uuid;
	
//	private AlignmentType Alignment = AlignmentType.LAWFUL;
//	private HorseType Horse = null;
	
	public LocalDateTime InCombat = LocalDateTime.now().minusSeconds(15);
	public int Level = 0;
	public int XP = 0;
	public int HpRegen = 0;
	public int EnergyRegen = 0;
	private Inventory bank;
	
	
//	private int Dex = 0;
//	private int Str = 0;
//	private int Int = 0;
//	private int Vit = 0;
//	
//	private int Def = 0;
//	private int MagRes = 0;
//	private int Thorns = 0;
//	private int GoldFind = 0;
	
	/**
	 * Get the bank of the player.
	 * @return Player's Bank
	 */
	public Inventory getBank()	{ return bank; }
	
	/**
	 * Set the bank of the player.
	 * @param inv
	 */
	public void setBank(Inventory inv)	{ bank = inv; } 
	
}
