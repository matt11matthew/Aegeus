package aegeus.com.aegeus;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Mining implements Listener	{
	
	private JavaPlugin plugin;
	
	public Mining(JavaPlugin p) {
		plugin = p;
	}
	
	/*
	 * These are the values of exp that ores will give you on your pickaxe.
	 * Also includes the range of each ore so you don't get the same xp every time. 
	 * The respawn timers are in ticks, 20 ticks in 1 second.
	 */
	private final int COAL_EXP = 25, COAL_RANGE = 10, COAL_TIMER = 100;
	private final int REDSTONE_EXP = 50, REDSTONE_RANGE = 10, REDSTONE_TIMER = 100;
	private final int IRON_EXP = 100, IRON_RANGE = 15, IRON_TIMER = 100;
	private final int LAPIS_EXP = 150, LAPIS_RANGE = 25, LAPIS_TIMER = 100;
	private final int DIAMOND_EXP = 300, DIAMOND_RANGE = 35, DIAMOND_TIMER = 100;
	private final int EMERALD_EXP = 500, EMERALD_RANGE = 45, EMERALD_TIMER = 100;
	private final int GOLD_EXP = 1000, GOLD_RANGE = 50, GOLD_TIMER = 100;
	
	@EventHandler
	/**
	 * 
	 * Triggers when a person begins to start mining a block.  Used to apply mining fatigue and
	 */
	public void onMine(BlockDamageEvent e)	{
		
	}
	
	@EventHandler
	/**
	 * Triggers when a person breaks a block.
	 * @param e
	 */
	public void onBlockBreak(BlockBreakEvent e)	{
		if(e.getBlock().getType() == Material.COAL_ORE || 
			e.getBlock().getType() == Material.REDSTONE_ORE || 
			e.getBlock().getType() == Material.IRON_ORE || 
			e.getBlock().getType() == Material.LAPIS_ORE || 
			e.getBlock().getType() == Material.DIAMOND_ORE || 
			e.getBlock().getType() == Material.EMERALD_ORE || 
			e.getBlock().getType() == Material.GOLD_ORE)	{
			if(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.WOOD_PICKAXE &&
				e.getPlayer().getInventory().getItemInMainHand().getType() != Material.STONE_PICKAXE &&
				e.getPlayer().getInventory().getItemInMainHand().getType() != Material.IRON_PICKAXE &&
				e.getPlayer().getInventory().getItemInMainHand().getType() != Material.DIAMOND_PICKAXE &&
				e.getPlayer().getInventory().getItemInMainHand().getType() != Material.GOLD_PICKAXE) e.setCancelled(true);
			else	{
				e.setCancelled(true);
				giveExpAndLoot(e.getBlock(), e.getPlayer());
			}
		}
		else e.setCancelled(true);
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE) e.setCancelled(false);

	}
	
	private void giveExpAndLoot(Block blockBroken, Player p)	{
		ItemStack pickaxe = p.getInventory().getItemInMainHand();
		if(pickaxe.getType() == Material.WOOD_PICKAXE)	{
			if(blockBroken.getType() == Material.COAL_ORE)	{
				giveItem(new ItemStack(Material.COAL_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.COAL_ORE, COAL_TIMER);
			}
			else p.sendMessage(ChatColor.RED + "Your pickaxe is not high enough tier to mine this ore!");
		}
		else if(pickaxe.getType() == Material.STONE_PICKAXE)	{
			if(blockBroken.getType() == Material.COAL_ORE)	{
				giveItem(new ItemStack(Material.COAL_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.COAL_ORE, COAL_TIMER);
			}
			else if(blockBroken.getType() == Material.REDSTONE_ORE)	{
				giveItem(new ItemStack(Material.REDSTONE_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.REDSTONE_ORE, REDSTONE_TIMER);
			}
			else p.sendMessage(ChatColor.RED + "Your pickaxe is not high enough tier to mine this ore!");
		}
		else if(pickaxe.getType() == Material.IRON_PICKAXE)	{
			if(blockBroken.getType() == Material.COAL_ORE)	{
				giveItem(new ItemStack(Material.COAL_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.COAL_ORE, COAL_TIMER);
			}
			else if(blockBroken.getType() == Material.REDSTONE_ORE)	{
				giveItem(new ItemStack(Material.REDSTONE_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.REDSTONE_ORE, REDSTONE_TIMER);
			}
			else if(blockBroken.getType() == Material.IRON_ORE)	{
				giveItem(new ItemStack(Material.IRON_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.IRON_ORE, IRON_TIMER);
			}
			else if(blockBroken.getType() == Material.LAPIS_ORE)	{
				giveItem(new ItemStack(Material.LAPIS_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.LAPIS_ORE, LAPIS_TIMER);
			}
			else p.sendMessage(ChatColor.RED + "Your pickaxe is not high enough tier to mine this ore!");
		}
		else if(pickaxe.getType() == Material.DIAMOND_PICKAXE)	{
			if(blockBroken.getType() == Material.COAL_ORE)	{
				giveItem(new ItemStack(Material.COAL_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.COAL_ORE, COAL_TIMER);
			}
			else if(blockBroken.getType() == Material.REDSTONE_ORE)	{
				giveItem(new ItemStack(Material.REDSTONE_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.REDSTONE_ORE, REDSTONE_TIMER);
			}
			else if(blockBroken.getType() == Material.IRON_ORE)	{
				giveItem(new ItemStack(Material.IRON_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.IRON_ORE, IRON_TIMER);
			}
			else if(blockBroken.getType() == Material.LAPIS_ORE)	{
				giveItem(new ItemStack(Material.LAPIS_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.LAPIS_ORE, LAPIS_TIMER);
			}
			else if(blockBroken.getType() == Material.DIAMOND_ORE)	{
				giveItem(new ItemStack(Material.DIAMOND_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.DIAMOND_ORE, DIAMOND_TIMER);
			}
			else if(blockBroken.getType() == Material.EMERALD_ORE)	{
				giveItem(new ItemStack(Material.EMERALD_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.EMERALD_ORE, EMERALD_TIMER);
			}
			else p.sendMessage(ChatColor.RED + "Your pickaxe is not high enough tier to mine this ore!");
		}
		else if(pickaxe.getType() == Material.GOLD_PICKAXE)	{
			if(blockBroken.getType() == Material.COAL_ORE)	{
				giveItem(new ItemStack(Material.COAL_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.COAL_ORE, COAL_TIMER);
			}
			else if(blockBroken.getType() == Material.REDSTONE_ORE)	{
				giveItem(new ItemStack(Material.REDSTONE_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.REDSTONE_ORE, REDSTONE_TIMER);
			}
			else if(blockBroken.getType() == Material.IRON_ORE)	{
				giveItem(new ItemStack(Material.IRON_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.IRON_ORE, IRON_TIMER);
			}
			else if(blockBroken.getType() == Material.LAPIS_ORE)	{
				giveItem(new ItemStack(Material.LAPIS_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.LAPIS_ORE, LAPIS_TIMER);
			}
			else if(blockBroken.getType() == Material.DIAMOND_ORE)	{
				giveItem(new ItemStack(Material.DIAMOND_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.DIAMOND_ORE, DIAMOND_TIMER);
			}
			else if(blockBroken.getType() == Material.EMERALD_ORE)	{
				giveItem(new ItemStack(Material.EMERALD_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.EMERALD_ORE, EMERALD_TIMER);
			}
			else if(blockBroken.getType() == Material.GOLD_ORE)	{
				giveItem(new ItemStack(Material.GOLD_ORE), p);
				blockBroken.setType(Material.STONE);
				setTimer(blockBroken, Material.GOLD_ORE, GOLD_TIMER);
			}
		}
		else	{
			
		}
	}
	
	/**
	 * Give the player the ore and other materials, then set the respawn timer for the ore.
	 * @param item
	 * @param p
	 */
	private void giveItem(ItemStack item, Player p)	{
		if(p.getInventory().firstEmpty() != -1)	{ //Does the job for now, improve it later.
			p.getInventory().addItem(item);
		}
		else	{ //There are no empty slots in the player's inventory, so it will be dropped on the ground
			p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Your item was dropped on the ground because you do not have enough space to hold it.");
		}
	}
	
	/**
	 * 
	 * Set a block(b) type to d after a certain amount of time(t), in ticks.
	 * @param b
	 * @param d
	 * @param t
	 */
	private void setTimer(Block b, Material d, int t)	{
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()	{
			public void run() {
				b.setType(d);
			}
			
		}
				, t);
	}
}
