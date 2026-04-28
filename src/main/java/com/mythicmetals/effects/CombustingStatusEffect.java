package com.mythicmetals.effects;

import com.mythicmetals.MythicMetals;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CombustingStatusEffect extends MobEffect {
    public CombustingStatusEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onEntityRemoval(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onEntityRemoval(entity, amplifier, reason);
        entity.getData(MythicMetals.COMBUSTION_COOLDOWN).setCooldown(500);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
