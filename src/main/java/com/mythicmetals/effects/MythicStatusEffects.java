package com.mythicmetals.effects;

import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

public class MythicStatusEffects {

    public static final MobEffect WORMHOLE_SPECIAL = new WormholeSpecial(MobEffectCategory.HARMFUL, 133337);
    public static final MobEffect HEAT = new StatusEffect(MobEffectCategory.HARMFUL, 16747008);
    public static final MobEffect COMBUSTION = new CombustingStatusEffect(MobEffectCategory.HARMFUL, 16747008)
        .addAttributeModifier(MythicEntityAttributes.FIRE_VULNERABILITY, RegistryHelper.id("fire_vulnerability"), 1.0, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(EntityAttributes.GENERIC_BURNING_TIME, RegistryHelper.id("burn_time_reduction"), 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public static void init() {
        Registry.register(BuiltInRegistries.STATUS_EFFECT, RegistryHelper.id("wormhole_special"), WORMHOLE_SPECIAL);
        Registry.register(BuiltInRegistries.STATUS_EFFECT, RegistryHelper.id("heat"), HEAT);
        Registry.register(BuiltInRegistries.STATUS_EFFECT, RegistryHelper.id("combustion"), COMBUSTION);

    }

}
