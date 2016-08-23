package com.aegeus.aegeus.player;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Minecart;
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
	public int level = 0;
	public int hp = 0;
	public int hpRegen = 0;
	public int energyRegen = 0;
	private int gold = 0;
	private Inventory bank;
	public boolean isBankWithdraw = false;
	
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
	
	public int getLevel()	{ return level;	}
	
	public void setLevel(int l)	{ level = l; }
	
	public int getHP()	{ return hp;	}
	
	public int getHPRegen()	{ return hpRegen; }
	
	public void setHPRegen(int t)	{ hpRegen = t; }
	
	public int getEnergyRegen()	{ return energyRegen; }
	
	public void setEnergyRegen(int t)	{ energyRegen = t; }
	
}
