package com.mythicmetals.misc;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.MythicMetals;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.*;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * A helper class containing methods for registering various blocks and items.
 *
 * @author Noaaan
 */
public class RegistryHelper {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MythicMetals.MOD_ID);

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MythicMetals.MOD_ID);

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MythicMetals.MOD_ID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MythicMetals.MOD_ID);

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MythicMetals.MOD_ID);

    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, MythicMetals.MOD_ID);

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, MythicMetals.MOD_ID);

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, MythicMetals.MOD_ID);

    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES =
            DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, MythicMetals.MOD_ID);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MythicMetals.MOD_ID);

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, MythicMetals.MOD_ID);

    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, MythicMetals.MOD_ID);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITY_TYPES.register(bus);
        BLOCK_ENTITY_TYPES.register(bus);
        DATA_COMPONENTS.register(bus);
        ATTRIBUTES.register(bus);
        POTIONS.register(bus);
        LOOT_CONDITION_TYPES.register(bus);
        MOB_EFFECTS.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        POI_TYPES.register(bus);
        CONDITION_SERIALIZERS.register(bus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MythicMetals.MOD_ID, path);
    }

    public static <T extends Block> DeferredBlock<T> blockOnly(
            String name,
            Supplier<T> blockSupplier
    ) {

        return BLOCKS.register(name, blockSupplier);
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(
            String name,
            Supplier<T> blockSupplier,
            Item.Properties itemProperties
    ) {

        DeferredBlock<T> block =
                BLOCKS.register(name, blockSupplier);

        ITEMS.register(name,
                () -> new BlockItem(block.get(), itemProperties));

        return block;
    }



    public static <T extends Block> DeferredBlock<T> block(
            String path,
            Supplier<T> blockSupplier
    ) {
        DeferredBlock<T> block = BLOCKS.register(path, blockSupplier);

        ITEMS.register(path,
                () -> new BlockItem(
                        block.get(),
                        new Item.Properties()
                                .group(MythicMetals.TABBED_GROUP)
                                .tab(1)
                )
        );

        return block;
    }

    public static <T extends Block> DeferredBlock<T> block(
            String path,
            Supplier<T> blockSupplier,
            boolean fireproof,
            boolean uncommon
    ) {
        DeferredBlock<T> block = BLOCKS.register(path, blockSupplier);

        Item.Properties properties = new Item.Properties()
                .group(MythicMetals.TABBED_GROUP)
                .tab(1);

        if (fireproof) {
            properties.fireResistant();
        }

        if (uncommon) {
            properties.rarity(Rarity.UNCOMMON);
        }

        ITEMS.register(path,
                () -> new BlockItem(block.get(), properties)
        );

        return block;
    }

    public static <T extends Item> DeferredItem<T> item(
            String path,
            Supplier<T> supplier
    ) {
        return ITEMS.register(path, supplier);
    }

    public static DeferredHolder<LootItemConditionType, LootItemConditionType> lootConditionType(
            String path,
            MapCodec<? extends LootItemCondition> lootCodec
    ) {
        return LOOT_CONDITION_TYPES.register(path,
                () -> new LootItemConditionType(lootCodec));
    }

    public static DeferredHolder<LootItemConditionType, LootItemConditionType> lootConditions(
            String path,
            MapCodec<? extends LootItemCondition> lootCodec
    ) {
        return LOOT_CONDITION_TYPES.register(path,
                () -> new LootItemConditionType(lootCodec));
    }

    public static DeferredHolder<MapCodec<? extends ICondition>, MapCodec<? extends ICondition>> conditionSerializers(
            String path,
            MapCodec<? extends ICondition> lootCodec
    ) {
        return CONDITION_SERIALIZERS.register(path,
                () -> lootCodec);
    }

    public static DeferredHolder<PoiType, PoiType> poiType(
            String path,
            Supplier<PoiType> poiTypeSupplier
    ) {
        return POI_TYPES.register(path,
                poiTypeSupplier);
    }

    public static DeferredHolder<Attribute, Attribute> entityAttribute(
            String path,
            Supplier<Attribute> supplier
    ) {
        return ATTRIBUTES.register(path, supplier);
    }

    public static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> dataComponentType(
            String path,
            UnaryOperator<DataComponentType.Builder<T>> builderOperator
    ) {
        return DATA_COMPONENTS.register(path,
                () -> builderOperator
                        .apply(DataComponentType.builder())
                        .build());
    }

    public static DeferredHolder<Potion, Potion> potion(
            String name,
            MobEffectInstance statusEffectInstance
    ) {
        return POTIONS.register(name,
                () -> new Potion(statusEffectInstance));
    }

    public static DeferredHolder<MobEffect, MobEffect> mobEffect(
            String name,
            Supplier<MobEffect> statusEffectInstance
    ) {
        return MOB_EFFECTS.register(name,
                statusEffectInstance);
    }

    public static <T extends Recipe<?>> DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> recipeSerializer(
            String name,
            Supplier<RecipeSerializer<T>> statusEffectInstance
    ) {
        return RECIPE_SERIALIZERS.register(name,
                statusEffectInstance);
    }

    public static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> entityType(String path, Supplier<EntityType<T>> type) {
        return ENTITY_TYPES.register(path, type);
    }

    public static void item(String path, Item item) {
        Registry.register(BuiltInRegistries.ITEM, id(path), item);
    }

    public static void block(String path, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, id(path), block);
        Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(1)));
    }

    public static void block(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(BuiltInRegistries.BLOCK, id(path), block);
            Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(1).fireResistant()));
        } else {
            block(path, block);
        }
    }

    public static void block(String path, Block block, boolean fireproof, boolean uncommon) {
        if (uncommon) {
            Registry.register(BuiltInRegistries.BLOCK, id(path), block);
            Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(1).rarity(Rarity.UNCOMMON)));
        } else {
            block(path, block, fireproof);
        }
    }

    public static void block(String path, Block block, OwoItemGroup group) {
        Registry.register(BuiltInRegistries.BLOCK, id(path), block);
        Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(group)));
    }

    public static void block(String path, Block block, OwoItemGroup group, boolean fireproof) {
        if (fireproof) {
            Registry.register(BuiltInRegistries.BLOCK, id(path), block);
            Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(group).fireResistant()));
        } else {
            block(path, block, group);
        }
    }

    public static void blockOnly(String path, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, id(path), block);
    }

    public static void entityType(String path, EntityType<?> type) {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, RegistryHelper.id(path), type);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, RegistryHelper.id(path));
    }

    /*
    public static LootItemConditionType lootConditionType(String path, MapCodec<? extends LootItemCondition> lootCodec) {
        return Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, RegistryHelper.id(path), new LootItemConditionType(lootCodec));
    }
     */

    public static void blockEntity(String path, BlockEntityType<?> type) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, RegistryHelper.id(path), type);
    }

    public static Holder<Attribute> entityAttribute(String path, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, id(path), attribute);
    }

    public static Holder<MobEffect> getEntry(MobEffect effect) {
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
    }

    public static Holder<ArmorMaterial> getEntry(ArmorMaterial material) {
        return BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(material);
    }

    public static Holder<Potion> getEntry(Potion potion) {
        return BuiltInRegistries.POTION.wrapAsHolder(potion);
    }

    /*
    public static <T> DataComponentType<T> dataComponentType(String path, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id(path), builderOperator.apply(DataComponentType.builder()).build());
    }
     */

    /*
    public static Holder<Potion> potion(String name, MobEffectInstance statusEffectInstance) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, id(name), new Potion(statusEffectInstance));
    }
     */
}
