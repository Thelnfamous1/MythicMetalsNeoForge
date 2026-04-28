package com.mythicmetals.misc;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.MythicMetals;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.item.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.registry.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import java.util.function.UnaryOperator;

/**
 * A helper class containing methods for registering various blocks and items.
 *
 * @author Noaaan
 */
public class RegistryHelper {

    public static ResourceLocation id(String path) {
        return ResourceLocation.of(MythicMetals.MOD_ID, path);
    }

    public static void item(String path, Item item) {
        Registry.register(Registries.ITEM, id(path), item);
    }

    public static void block(String path, Block block) {
        Registry.register(Registries.BLOCK, id(path), block);
        Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(MythicMetals.TABBED_GROUP).tab(1)));
    }

    public static void block(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(MythicMetals.TABBED_GROUP).tab(1).fireproof()));
        } else {
            block(path, block);
        }
    }

    public static void block(String path, Block block, boolean fireproof, boolean uncommon) {
        if (uncommon) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(MythicMetals.TABBED_GROUP).tab(1).rarity(Rarity.UNCOMMON)));
        } else {
            block(path, block, fireproof);
        }
    }

    public static void block(String path, Block block, OwoItemGroup group) {
        Registry.register(Registries.BLOCK, id(path), block);
        Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static void block(String path, Block block, OwoItemGroup group, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(group).fireproof()));
        } else {
            block(path, block, group);
        }
    }

    public static void blockOnly(String path, Block block) {
        Registry.register(Registries.BLOCK, id(path), block);
    }

    public static void entityType(String path, BlockEntityType<?> type) {
        Registry.register(Registries.ENTITY_TYPE, RegistryHelper.id(path), type);
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String path) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, RegistryHelper.id(path));
    }

    public static LootItemConditionType lootConditionType(String path, MapCodec<? extends LootItemCondition> lootCodec) {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, RegistryHelper.id(path), new LootConditionType(lootCodec));
    }

    public static void blockEntity(String path, BlockEntityType<?> type) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, RegistryHelper.id(path), type);
    }

    public static Holder<Attribute> entityAttribute(String path, Attribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, id(path), attribute);
    }

    public static Holder<MobEffect> getEntry(MobEffect effect) {
        return Registries.STATUS_EFFECT.getEntry(effect);
    }

    public static Holder<ArmorMaterial> getEntry(ArmorMaterial material) {
        return Registries.ARMOR_MATERIAL.getEntry(material);
    }

    public static Holder<Potion> getEntry(Potion potion) {
        return Registries.POTION.getEntry(potion);
    }

    public static <T> DataComponentType<T> dataComponentType(String path, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id(path), builderOperator.apply(DataComponentType.builder()).build());
    }

    public static Holder<Potion> potion(String name, MobEffectInstance statusEffectInstance) {
        return Registry.registerReference(Registries.POTION, id(name), new Potion(statusEffectInstance));
    }
}
