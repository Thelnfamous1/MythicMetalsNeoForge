package com.mythicmetals.item;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.core.Holder;

public class MythicPotions {

    public static void init() {}

    static {
        Holder<Potion> STRONG_LUCK = RegistryHelper.potion("strong_luck", new StatusEffectInstance(MobEffects.LUCK, 6000, 1));
    }
}
