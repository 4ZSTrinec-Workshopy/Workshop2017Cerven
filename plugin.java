public class Plugin extends JavaPlugin implements Listener{
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents( this, this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("heal")) {
			 if (args.length == 0) {
				 	player.sendMessage(ChatColor.GRAY + "You Have Been Healed!");
			 }
			 	player.setHealth(20.0);
			 	player.setFoodLevel(20);
			 	player.setFireTicks(0);
		}
		
		return true;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(!(e.getItem().getType() == Material.IRON_SPADE)) return;
		
		if(!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		Fireball f = e.getPlayer().launchProjectile(Fireball.class);
		f.setIsIncendiary(true);
		f.setYield(0);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Fireball) {
			Fireball f = (Fireball) e.getDamager();
			if(f.getShooter() instanceof Player) {
				Player s = (Player) f.getShooter();
				if(s.getItemInHand().getType() == Material.IRON_SPADE) {
					e.setDamage(10);
				}
			}
		}
	}
}
