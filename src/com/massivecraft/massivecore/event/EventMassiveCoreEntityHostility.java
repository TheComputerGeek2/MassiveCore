package com.massivecraft.massivecore.event;

import com.massivecraft.massivecore.Hostility;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EventMassiveCoreEntityHostility extends EventMassiveCore
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private static final HandlerList handlers = new HandlerList();
	@Override public HandlerList getHandlers() { return handlers; }
	public static HandlerList getHandlerList() { return handlers; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private EntitySpawnEvent spawnEvent;
	public EntitySpawnEvent getEntitySpawnEvent() { return this.spawnEvent; }
	
	private Hostility resolvedHostility;
	public Hostility getHostility() { return this.resolvedHostility; }
	public void setHostility(Hostility hostility) { this.resolvedHostility = hostility; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public EventMassiveCoreEntityHostility(final EntitySpawnEvent spawnEvent)
	{
		this(spawnEvent, Hostility.NEUTRAL);
	}
	
	public EventMassiveCoreEntityHostility(final EntitySpawnEvent spawnEvent, Hostility hostility)
	{
		this.spawnEvent = spawnEvent;
		this.resolvedHostility = hostility;
	}
	
	// -------------------------------------------- //
	// OVERRIDE: CANCELABLE
	// -------------------------------------------- //
	
	@Override public void setCancelled(boolean canceled) { this.spawnEvent.setCancelled(canceled); }
	@Override public boolean isCancelled() { return this.spawnEvent.isCancelled(); }
	
}
