package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MythicPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, MythicMetals.MOD_ID);
    public static final DeferredHolder<Potion, Potion> STRONG_LUCK = RegistryHelper.potion("strong_luck", () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 6000, 1)));

    public static void init() {}

}
