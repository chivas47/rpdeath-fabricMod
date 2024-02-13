
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.rpdeathfabric.init;

import net.rpdeathfabric.procedures.DeathscreenDisplayOverlayIngameProcedure;
import net.rpdeathfabric.procedures.DeathpunishProcedure;

@SuppressWarnings("InstantiationOfUtilityClass")
public class RpdeathfabricModProcedures {
	public static void load() {
		new DeathpunishProcedure();
		new DeathscreenDisplayOverlayIngameProcedure();
	}
}
