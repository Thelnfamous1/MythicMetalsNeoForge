package com.mythicmetals.misc;

import net.minecraft.world.damagesource.DamageType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

/**
 * All of these damage types are present in the built-in datapack
 */
public class MythicDamageTypes {
    public static final ResourceKey<DamageType> ASCENSION = ResourceKey.of(Registries.DAMAGE_TYPE, RegistryHelper.id("ascension"));
    public static final ResourceKey<DamageType> BANGLUM_NUKE = ResourceKey.of(Registries.DAMAGE_TYPE, RegistryHelper.id("banglum_nuke"));
    public static final ResourceKey<DamageType> CARMOT_BELL = ResourceKey.of(Registries.DAMAGE_TYPE, RegistryHelper.id("carmot_bell"));
    public static final ResourceKey<DamageType> STAR_PLATINUM_ARROW = ResourceKey.of(Registries.DAMAGE_TYPE, RegistryHelper.id("star_platinum_arrow"));
}
