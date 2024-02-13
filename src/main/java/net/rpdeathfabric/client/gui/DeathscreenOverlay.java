package net.rpdeathfabric.client.gui;

import net.rpdeathfabric.procedures.DeathscreenDisplayOverlayIngameProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Environment(EnvType.CLIENT)
public class DeathscreenOverlay {
	public static void render(GuiGraphics guiGraphics, float tickDelta) {
		int posX = Minecraft.getInstance().getWindow().getGuiScaledWidth() / 2;
		int posY = Minecraft.getInstance().getWindow().getGuiScaledHeight() / 2;
		Level _world = null;
		double _x = 0;
		double _y = 0;
		double _z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			_world = entity.level();
			_x = entity.getX();
			_y = entity.getY();
			_z = entity.getZ();
		}
		Level world = _world;
		double x = _x;
		double y = _y;
		double z = _z;
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		if (DeathscreenDisplayOverlayIngameProcedure.execute()) {
			guiGraphics.blit(new ResourceLocation("rpdeathfabric:textures/screens/dead-screen.png"), posX + -213, posY + -120, 0, 0, 427, 240, 427, 240);
		}
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
