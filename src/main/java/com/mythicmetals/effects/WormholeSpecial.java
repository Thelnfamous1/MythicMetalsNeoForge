package com.mythicmetals.effects;

import io.wispforest.owo.ops.WorldOps;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sound.*;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.gameevent.GameEvent;

/**
 * Joke status effect meant to be integrated with Spectrums Titration barrel
 * Teleports you around
 */
public final class WormholeSpecial extends MobEffect {

    public WormholeSpecial(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onApplied(LivingEntity user, int amplifier) {
        var world = user.getWorld();
        if (!user.getWorld().isClient) {
            for (int i = 0; i < 20; i++) {
                double x = user.getX() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                double y = Mth.clamp(
                    user.getY() + (double) (user.getRandom().nextInt(24) - 8),
                    world.getBottomY(),
                    world.getBottomY() + ((ServerLevel) world).getLogicalHeight() - 1
                );
                double z = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 24.0;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }

                Vec3 vec3d = user.getPos();
                if (user.teleport(x, y, z, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Context.of(user));
                    SoundEvent soundEvent = user instanceof Fox ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    WorldOps.playSound(world, user.getPos(), soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    break;
                }
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 60 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        }
        return true;
    }
}
