package com.aegeus.aegeus.player;

import java.time.LocalDateTime;

import org.bukkit.boss.BossBar;

public class PlayerData {
	
	public BossBar BossBarHP;
	
//	private AlignmentType Alignment = AlignmentType.LAWFUL;
//	private HorseType Horse = null;
	
	public LocalDateTime InCombat = LocalDateTime.now().minusSeconds(15);
	public int Level = 0;
	public int XP = 0;
	public int HpRegen = 0;
	public int EnergyRegen = 0;
	
//	private int Dex = 0;
//	private int Str = 0;
//	private int Int = 0;
//	private int Vit = 0;
//	
//	private int Def = 0;
//	private int MagRes = 0;
//	private int Thorns = 0;
//	private int GoldFind = 0;
	
}
