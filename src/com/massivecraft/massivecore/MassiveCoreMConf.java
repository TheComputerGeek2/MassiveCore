package com.massivecraft.massivecore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.permissions.Permissible;

import com.massivecraft.massivecore.collections.MassiveMap;
import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.command.editor.annotation.EditorType;
import com.massivecraft.massivecore.command.type.TypeMillisDiff;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanOn;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.PermissionUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.xlib.mongodb.WriteConcern;

@EditorName("config")
public class MassiveCoreMConf extends Entity<MassiveCoreMConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MassiveCoreMConf i;
	public static MassiveCoreMConf get() { return i; }
	
	// -------------------------------------------- //
	// ALIASES
	// -------------------------------------------- //
	// Base command aliases.
	
	public List<String> aliasesMcore = MUtil.list("massivecore", "mcore");
	public List<String> aliasesUsys = MUtil.list("usys");
	public List<String> aliasesMstore = MUtil.list("massivestore", "mstore");
	public List<String> aliasesBuffer = MUtil.list("buffer");
	public List<String> aliasesCmdurl = MUtil.list("cmdurl");
	
	// -------------------------------------------- //
	// GENERAL
	// -------------------------------------------- //
	// General configuration options.
	
	public String taskServerId = null;
	public boolean versionSynchronizationEnabled = true;
	public int tabCompletionLimit = 100;
	public boolean recipientChatEventEnabled = true;
	
	// -------------------------------------------- //
	// PERMISSIONS FORMATS
	// -------------------------------------------- //
	// Permission denied formatting.
	
	public Map<String, String> permissionDeniedFormats = MUtil.map(
		"some.awesome.permission.node", "<b>You must be awesome to %s<b>.",
		"some.derp.permission.node.1", "derp",
		"some.derp.permission.node.2", "derp",
		"some.derp.permission.node.3", "derp",
		"derp", "<b>Only derp people can %s<b>.\n<i>Ask a moderator to become derp."
	);
	
	public String getPermissionDeniedFormat(String permissionName)
	{
		Map<String, String> map = this.permissionDeniedFormats;
		String ret = map.get(permissionName);
		if (ret == null) return null;
		ret = MUtil.recurseResolveMap(ret, map);
		return ret;
	}
	
	// -------------------------------------------- //
	// TP DELAY
	// -------------------------------------------- //
	// Teleportation delay permissions.
	
	public Map<String, Integer> permissionToTpdelay = MUtil.map(
		"massivecore.notpdelay", 0,
		"default", 10
	);
	
	public int getTpdelay(Permissible permissible)
	{
		Integer ret = PermissionUtil.pickFirstVal(permissible, permissionToTpdelay);
		if (ret == null) ret = 0;
		return ret;
	}
	
	// -------------------------------------------- //
	// DELETE FILES
	// -------------------------------------------- //
	// Delete certain files for system cleanliness.
	
	public List<String> deleteFiles = new ArrayList<>();
	
	// -------------------------------------------- //
	// VARIABLES
	// -------------------------------------------- //
	// Chat and command variables.
	
	public String variableBookName = "***book***";
	public boolean variableBookEnabled = true;
	
	public String variableBufferName = "***buffer***";
	public boolean variableBufferEnabled = true;
	
	// -------------------------------------------- //
	// CLICK
	// -------------------------------------------- //
	// Button click sound configuration.
	
	public SoundEffect clickSound = SoundEffect.valueOf("UI_BUTTON_CLICK", 0.75f, 1.0f);
	
	// -------------------------------------------- //
	// MSTORE
	// -------------------------------------------- //
	// The database system.
	
	@EditorType(TypeMillisDiff.class)
	public volatile long millisBetweenLocalPoll = TimeUnit.MILLIS_PER_MINUTE * 5;
	@EditorType(TypeMillisDiff.class)
	public volatile long millisBetweenRemotePollWithoutPusher = TimeUnit.MILLIS_PER_SECOND * 10;
	@EditorType(TypeMillisDiff.class)
	public volatile long millisBetweenRemotePollWithPusher = TimeUnit.MILLIS_PER_MINUTE * 1;
	
	@EditorType(TypeBooleanOn.class)
	public boolean warnOnLocalAlter = false;
	
	// -------------------------------------------- //
	// MONGODB
	// -------------------------------------------- //
	// The database system MongoDB driver.
	
	public boolean catchingMongoDbErrorsOnSave = true;
	public boolean catchingMongoDbErrorsOnDelete = true;
	
	public static WriteConcern getMongoDbWriteConcern(boolean catchingErrors) { return catchingErrors ? WriteConcern.ACKNOWLEDGED : WriteConcern.UNACKNOWLEDGED; }
	public WriteConcern getMongoDbWriteConcernSave() { return getMongoDbWriteConcern(this.catchingMongoDbErrorsOnSave); }
	public WriteConcern getMongoDbWriteConcernDelete() { return getMongoDbWriteConcern(this.catchingMongoDbErrorsOnDelete); }
	
	// -------------------------------------------- //
	// DEVELOPER
	// -------------------------------------------- //
	
	public boolean debugWriters = false;
	public boolean testsEnabled = false;
	
	// -------------------------------------------- //
	// SPONSOR
	// -------------------------------------------- //
	// URL connections to http://sponsorinfo.massivecraft.com/
	
	public long sponsorUpdateMillis = 0;
	public boolean sponsorEnabled = true;
	
	// -------------------------------------------- //
	// MCSTATS
	// -------------------------------------------- //
	// URL connections to http://mcstats.org/
	
	public boolean mcstatsEnabled = true;

	// -------------------------------------------- //
	// LORE SORTING
	// -------------------------------------------- //

	public boolean loreSortOnInventoryClick = false;
	public boolean loreSortOnInventoryOpen = false;

	public Map<String, Integer> lorePrioritiesPrefix = new MassiveMap<>();
	public Map<String, Integer> lorePrioritiesRegex = new MassiveMap<>();

	// -------------------------------------------- //
	// HOSTILITY DEFAULTS
	// -------------------------------------------- //
	
	public Map<String, Hostility> entityHostilityDefaults = new MassiveMap<>(
		"BAT", Hostility.PASSIVE,
		"BLAZE", Hostility.HOSTILE,
		"CAVE_SPIDER", Hostility.HOSTILE,
		"CREEPER", Hostility.HOSTILE,
		"COW", Hostility.PASSIVE,
		"CHICKEN", Hostility.PASSIVE,
		"ENDERMITE", Hostility.HOSTILE,
		"ENDERMAN", Hostility.HOSTILE,
		"ENDER_DRAGON", Hostility.HOSTILE,
		"GUARDIAN", Hostility.HOSTILE,
		"GIANT", Hostility.HOSTILE,
		"GHAST", Hostility.HOSTILE,
		"HORSE", Hostility.PASSIVE,
		"IRON_GOLEM", Hostility.NEUTRAL,
		"MUSHROOM_COW", Hostility.PASSIVE,
		"MAGMA_CUBE", Hostility.HOSTILE,
		"OCELOT", Hostility.PASSIVE,
		"POLAR_BEAR", Hostility.HOSTILE,
		"PIG_ZOMBIE", Hostility.HOSTILE,
		"PIG", Hostility.PASSIVE,
		"RABBIT", Hostility.PASSIVE,
		"SQUID", Hostility.PASSIVE,
		"SPIDER", Hostility.HOSTILE,
		"SNOWMAN", Hostility.PASSIVE,
		"SLIME", Hostility.HOSTILE,
		"SHEEP", Hostility.PASSIVE,
		"SHULKER", Hostility.HOSTILE,
		"SILVERFISH", Hostility.HOSTILE,
		"SKELETON", Hostility.HOSTILE,
		"VILLAGER", Hostility.PASSIVE,
		"WOLF", Hostility.NEUTRAL,
		"WITHER", Hostility.HOSTILE,
		"WITCH", Hostility.HOSTILE,
		"ZOMBIE", Hostility.HOSTILE
	);
	
}
