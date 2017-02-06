package com.massivecraft.massivecore.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Hostility;
import com.massivecraft.massivecore.MassiveCoreMConf;
import com.massivecraft.massivecore.event.EventMassiveCoreEntityHostility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EngineMassiveCoreHostility extends Engine
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineMassiveCoreHostility i = new EngineMassiveCoreHostility();
	public static EngineMassiveCoreHostility get() { return i; }
	
	// -------------------------------------------- //
	// LISTENER
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void entitySpawnLow(EntitySpawnEvent event)
	{
		Entity entity = event.getEntity();
		if (entity == null) return;
		
		Hostility level = this.determineHostilityStatus(entity);
		EventMassiveCoreEntityHostility hostilityEvent = new EventMassiveCoreEntityHostility(event, level);
		hostilityEvent.run();
	}
	
	public Hostility determineHostilityStatus(Entity spawn)
	{
		Hostility ret = MassiveCoreMConf.get().entityHostilityDefaults.get(spawn.getType().name());
		if (ret == null) ret = Hostility.PASSIVE;
		
		// apparently we need a special case for the killer bunny
		// the only hostile rabbit under the same entity type
		if (isKillerBunny(spawn)) ret = Hostility.HOSTILE;
		
		return ret;
	}
	
	private boolean isKillerBunny(Entity spawn)
	{
		if (!spawn.getType().name().equals("RABBIT")) return false;
		Rabbit r = (Rabbit)spawn;
		return r.getRabbitType().equals(Rabbit.Type.THE_KILLER_BUNNY);
	}
	
}
