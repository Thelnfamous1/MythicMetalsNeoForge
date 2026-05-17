package com.mythicmetals.misc;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.MythicArmorMaterials;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.MythicPotions;
import com.mythicmetals.recipe.MythicRecipeSerializers;
import com.mythicmetals.registry.RegisterCriteria;
import com.mythicmetals.registry.RegisterLootConditions;
import com.mythicmetals.registry.RegisterPointOfInterests;
import com.mythicmetals.registry.RegisterSounds;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
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

    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, MythicMetals.MOD_ID);

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MythicMetals.MOD_ID, path);
    }

    public static DeferredHolder<ArmorMaterial, ArmorMaterial> armorMaterial(
            String name,
            Supplier<ArmorMaterial> armorMaterialSupplier
    ) {

        return MythicArmorMaterials.ARMOR_MATERIALS.register(name, armorMaterialSupplier);
    }

    public static <T extends Block> DeferredBlock<T> blockOnly(
            String name,
            Supplier<T> blockSupplier
    ) {

        return MythicBlocks.BLOCKS.register(name, blockSupplier);
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(
            String name,
            Supplier<T> blockSupplier,
            Item.Properties itemProperties
    ) {

        DeferredBlock<T> block =
                MythicBlocks.BLOCKS.register(name, blockSupplier);

        MythicItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), itemProperties));

        return block;
    }



    public static <T extends Block> DeferredBlock<T> block(
            String path,
            Supplier<T> blockSupplier
    ) {
        DeferredBlock<T> block = MythicBlocks.BLOCKS.register(path, blockSupplier);

        MythicItems.ITEMS.register(path,
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
        DeferredBlock<T> block = MythicBlocks.BLOCKS.register(path, blockSupplier);

        Item.Properties properties = new Item.Properties()
                .group(MythicMetals.TABBED_GROUP)
                .tab(1);

        if (fireproof) {
            properties.fireResistant();
        }

        if (uncommon) {
            properties.rarity(Rarity.UNCOMMON);
        }

        MythicItems.ITEMS.register(path,
                () -> new BlockItem(block.get(), properties)
        );

        return block;
    }

    public static <T extends Item> DeferredItem<T> item(
            String path,
            Supplier<T> supplier
    ) {
        return MythicItems.ITEMS.register(path, supplier);
    }

    public static DeferredHolder<LootItemConditionType, LootItemConditionType> lootConditionType(
            String path,
            MapCodec<? extends LootItemCondition> lootCodec
    ) {
        return RegisterLootConditions.LOOT_CONDITION_TYPES.register(path,
                () -> new LootItemConditionType(lootCodec));
    }

    public static DeferredHolder<LootItemConditionType, LootItemConditionType> lootConditions(
            String path,
            MapCodec<? extends LootItemCondition> lootCodec
    ) {
        return RegisterLootConditions.LOOT_CONDITION_TYPES.register(path,
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
        return RegisterPointOfInterests.POI_TYPES.register(path,
                poiTypeSupplier);
    }

    public static DeferredHolder<Attribute, Attribute> entityAttribute(
            String path,
            Supplier<Attribute> supplier
    ) {
        return MythicEntityAttributes.ATTRIBUTES.register(path, supplier);
    }

    public static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> dataComponentType(
            String path,
            UnaryOperator<DataComponentType.Builder<T>> builderOperator
    ) {
        return MythicDataComponents.DATA_COMPONENTS.register(path,
                () -> builderOperator
                        .apply(DataComponentType.builder())
                        .build());
    }

    public static DeferredHolder<Potion, Potion> potion(
            String name,
            MobEffectInstance statusEffectInstance
    ) {
        return MythicPotions.POTIONS.register(name,
                () -> new Potion(statusEffectInstance));
    }

    public static DeferredHolder<Potion, Potion> potion(
            String name,
            Supplier<Potion> potionSupplier
    ) {
        return MythicPotions.POTIONS.register(name,
                potionSupplier);
    }

    public static DeferredHolder<MobEffect, MobEffect> mobEffect(
            String name,
            Supplier<MobEffect> statusEffectInstance
    ) {
        return MythicStatusEffects.MOB_EFFECTS.register(name,
                statusEffectInstance);
    }

    public static DeferredHolder<SoundEvent, SoundEvent> soundEvent(
            String name,
            Supplier<SoundEvent> soundEventSupplier
    ) {
        return RegisterSounds.SOUND_EVENTS.register(name,
                soundEventSupplier);
    }

    public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> blockEntity(
            String name,
            Supplier<BlockEntityType<T>> blockEntityTypeSupplier
    ) {
        return RegisterBlockEntityTypes.BLOCK_ENTITY_TYPES.register(name,
                blockEntityTypeSupplier);
    }

    public static <T extends Recipe<?>> DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> recipeSerializer(
            String name,
            Supplier<RecipeSerializer<T>> statusEffectInstance
    ) {
        return MythicRecipeSerializers.RECIPE_SERIALIZERS.register(name,
                statusEffectInstance);
    }

    public static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> entityType(String path, Supplier<EntityType<T>> type) {
        return MythicEntities.ENTITY_TYPES.register(path, type);
    }

    public static <T extends CriterionTrigger<?>> DeferredHolder<CriterionTrigger<?>, T> triggerType(
            String name,
            Supplier<T> triggerSupplier
    ) {
        return RegisterCriteria.TRIGGER_TYPES.register(name,
                triggerSupplier);
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
