package com.mythicmetals.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mythicmetals.registry.RegisterLootConditions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public record RandomChanceWithLuckCondition(float chance) implements LootItemCondition {
    public static final MapCodec<RandomChanceWithLuckCondition> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance
            .group(Codec.FLOAT.fieldOf("chance")
                .forGetter(RandomChanceWithLuckCondition::chance))
            .apply(instance, RandomChanceWithLuckCondition::new)
    );

    @Override
    public LootItemConditionType getType() {
        return RegisterLootConditions.RANDOM_CHANCE_WITH_LUCK.get();
    }

    public boolean test(LootContext lootContext) {
        if (lootContext.getParam(LootContextParams.THIS_ENTITY) instanceof LivingEntity entity && entity.getAttributes().hasAttribute(Attributes.LUCK)) {
            double luckModifier = chance * (entity.getAttributeValue(Attributes.LUCK) / 10);
            return lootContext.getRandom().nextFloat() < this.chance + luckModifier;
        }
        return lootContext.getRandom().nextFloat() < this.chance;
    }

    public static LootItemCondition.Builder builder(float chance) {
        return () -> new RandomChanceWithLuckCondition(chance);
    }

}
