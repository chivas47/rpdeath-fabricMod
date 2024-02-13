/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.rpdeathfabric.init;

import net.rpdeathfabric.client.gui.DeathscreenOverlay;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class RpdeathfabricModOverlays {
	public static void load() {
		HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
			DeathscreenOverlay.render(matrices, tickDelta);
		});
	}
}
