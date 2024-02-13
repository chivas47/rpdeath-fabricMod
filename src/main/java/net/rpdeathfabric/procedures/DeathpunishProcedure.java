package net.rpdeathfabric.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class DeathpunishProcedure {
	public static boolean deathCheck = false;

	public DeathpunishProcedure() {
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			execute(newPlayer.level(), newPlayer.getX(), newPlayer.getY(), newPlayer.getZ(), newPlayer);
		});
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.goat_horn.sound.0")), SoundSource.PLAYERS, 5, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.goat_horn.sound.0")), SoundSource.PLAYERS, 5, 1, false);
			}
		}
		{
			Entity _ent = entity;
			_ent.teleportTo((world.getLevelData().getXSpawn()), (world.getLevelData().getYSpawn()), (world.getLevelData().getZSpawn()));
			if (_ent instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport((world.getLevelData().getXSpawn()), (world.getLevelData().getYSpawn()), (world.getLevelData().getZSpawn()), _ent.getYRot(), _ent.getXRot());
		}
		DeathpunishProcedure.deathCheck = true;
		DeathscreenDisplayOverlayIngameProcedure.execute();
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 6000, 100, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 6000, 255, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 219, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 6000, 128, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 6000, 128, false, false));
		new Object() {
			private int ticks = 0;

			public void startDelay(LevelAccessor world) {
				ServerTickEvents.END_SERVER_TICK.register((server) -> {
					this.ticks++;
					if (this.ticks == 6000) {
						DeathpunishProcedure.deathCheck = false;
						DeathscreenDisplayOverlayIngameProcedure.execute();
						if (entity instanceof LivingEntity _entity)
							_entity.removeAllEffects();
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.player.levelup")), SoundSource.PLAYERS, 20, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.player.levelup")), SoundSource.PLAYERS, 20, 1, false);
							}
						}
						{
							Entity _ent = entity;
							_ent.teleportTo((world.getLevelData().getXSpawn()), (world.getLevelData().getYSpawn()), (world.getLevelData().getZSpawn()));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((world.getLevelData().getXSpawn()), (world.getLevelData().getYSpawn()), (world.getLevelData().getZSpawn()), _ent.getYRot(), _ent.getXRot());
						}
						return;
					}
				});
			}
		}.startDelay(world);
	}
}
