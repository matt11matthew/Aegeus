package aegeus.com.aegeus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import aegeus.com.aegeus.obj.AegeusPlayer;
import aegeus.com.aegeus.util.Helper;
import net.minecraft.server.v1_10_R1.NBTTagCompound;

public class Statistics implements Listener {

	private JavaPlugin parent;

	public Statistics(JavaPlugin parent) {
		this.parent = parent;
		new BukkitRunnable(){
			@Override
			public void run() {
				for(Player player : parent.getServer().getOnlinePlayers()){
					if(player.getHealth() < player.getMaxHealth()
							&& !player.isDead()){
						double hp = player.getHealth();
						double maxhp = player.getMaxHealth();
						hp += (5 + playerData.get(player).HpRegen);
						if(hp > maxhp){
							hp = maxhp;
						}
						player.setHealth(hp);
						updateDisplay(player);
					}
				}
			}
		}.runTaskTimer(parent, 0, 20);
	}

	public static Map<Player, AegeusPlayer> playerData = new HashMap<>();

	/**
	 * Updates the statistics of an entity.
	 * 
	 * @param entity
	 *            Entity to update.
	 */
	public static void updateStats(LivingEntity entity) {
		
		// TODO This should be based off of NBT tags.
		
		List<ItemStack> items = new ArrayList<>();
		int Hp = 5;
		int HpRegen = 0;
		int EnergyRegen = 0;
//		int nstr = 0;
//		int ndef = 0;
//		int nvit = 0;
//		int nmagres = 0;
//		int nthorns = 0;
//		int ngoldfind = 0;
		
		if (entity.getEquipment().getHelmet() != null
				&& entity.getEquipment().getHelmet().getType() != Material.AIR) {
			items.add(entity.getEquipment().getLeggings());
		}
		if (entity.getEquipment().getChestplate() != null
				&& entity.getEquipment().getChestplate().getType() != Material.AIR) {
			items.add(entity.getEquipment().getLeggings());
		}
		if (entity.getEquipment().getLeggings() != null
				&& entity.getEquipment().getLeggings().getType() != Material.AIR) {
			items.add(entity.getEquipment().getLeggings());
		}
		if (entity.getEquipment().getBoots() != null
				&& entity.getEquipment().getBoots().getType() != Material.AIR) {
			items.add(entity.getEquipment().getBoots());
		}

		if(!items.isEmpty()){
			for(ItemStack item : items){
				net.minecraft.server.v1_10_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
				NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
				NBTTagCompound aegeus = (compound.hasKey("AegeusInfo")) ? compound.getCompound("AegeusInfo") : new NBTTagCompound();
				Hp += aegeus.getInt("Hp");
				HpRegen += (aegeus.hasKey("HpRegen")) ? aegeus.getInt("HpRegen") : 0;
				EnergyRegen += (aegeus.hasKey("EnergyRegen")) ? aegeus.getInt("EnergyRegen") : 0;
			}
		}
		
		if (entity.getType().equals(EntityType.PLAYER)) {
			Hp += 95;
			playerData.get((Player) entity).HpRegen = HpRegen;
			playerData.get((Player) entity).EnergyRegen = EnergyRegen;
		}

		entity.setMaxHealth(Hp);
		
		if (entity.getType().equals(EntityType.PLAYER)) {
			updateDisplay((Player) entity);
		}
	}

	/**
	 * Updates the statistics of a player.
	 * 
	 * @param parent
	 *            Parent plugin to use.
	 * @param player
	 *            Player to update.
	 */
	public static void updateStats(JavaPlugin parent, Player player) {
		updateStats((LivingEntity) player);
	}

	/**
	 * Updates the statistic display of a player.
	 * 
	 * @param player
	 *            Player to update.
	 */
	public static void updateDisplay(Player player) {
		if (playerData.get(player).BossBarHP == null) {
			// Create a new Hp BossBar for this player
			playerData.get(player).BossBarHP = Bukkit.createBossBar("", BarColor.GREEN, BarStyle.SEGMENTED_20);
			playerData.get(player).BossBarHP.addPlayer(player);
			playerData.get(player).BossBarHP.setVisible(true);
		}
		if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective("hp") == null) {
			// Create an objective for BelowNameHP
			Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("hp", "health");
			Bukkit.getScoreboardManager().getMainScoreboard().getObjective("hp")
					.setDisplayName(Helper.colorCodes("&c❤"));
			Bukkit.getScoreboardManager().getMainScoreboard().getObjective("hp").setDisplaySlot(DisplaySlot.BELOW_NAME);
		}
		// Set BelowNameHP
		Bukkit.getScoreboardManager().getMainScoreboard().getObjective("hp").getScore(player.getName())
				.setScore((int) Math.round(player.getHealth()));
		// Set HP BossBar
		playerData.get(player).BossBarHP.setProgress(player.getHealth() / player.getMaxHealth());
		playerData.get(player).BossBarHP.setTitle(Helper.colorCodes(
				"&a" + Math.round(player.getHealth()) + " / " + Math.round(player.getMaxHealth()) + " &lHP"));
	}

}
