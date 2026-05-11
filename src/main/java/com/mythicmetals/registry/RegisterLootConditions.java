package com.mythicmetals.registry;

import com.mythicmetals.misc.RandomChanceWithLuckCondition;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class RegisterLootConditions {

    private RegisterLootConditions() {
    }

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> RANDOM_CHANCE_WITH_LUCK = RegistryHelper.lootConditionType("random_chance_with_luck", RandomChanceWithLuckCondition.CODEC);

    public static void init() {

    }
}
