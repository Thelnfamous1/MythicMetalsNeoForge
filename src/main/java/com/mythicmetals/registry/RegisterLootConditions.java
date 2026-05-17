package com.mythicmetals.registry;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RandomChanceWithLuckCondition;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegisterLootConditions {

    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES =
            DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, MythicMetals.MOD_ID);
    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> RANDOM_CHANCE_WITH_LUCK = RegistryHelper.lootConditionType("random_chance_with_luck", RandomChanceWithLuckCondition.CODEC);

    private RegisterLootConditions() {
    }


    public static void init() {

    }
}
