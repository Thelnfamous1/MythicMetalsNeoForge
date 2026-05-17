package com.mythicmetals.block.entity;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.misc.RegistryHelper;
//import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

//import java.lang.reflect.Field;

public class RegisterBlockEntityTypes /*implements BlockEntityRegistryContainer*/ {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MythicMetals.MOD_ID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AquariumResonatorBlockEntity>> AQUARIUM_RESONATOR =
            RegistryHelper.blockEntity("aquarium_resonator", () -> BlockEntityType.Builder.of(AquariumResonatorBlockEntity::new, MythicBlocks.AQUARIUM_RESONATOR.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, RegistryHelper.id("aquarium_resonator").toString())));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnchantedMidasGoldBlockEntity>> ENCHANTED_MIDAS_GOLD_BLOCK =
            RegistryHelper.blockEntity("enchanted_midas_gold_block", () -> BlockEntityType.Builder.of(EnchantedMidasGoldBlockEntity::new, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, RegistryHelper.id("enchanted_midas_gold_block").toString())));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CarmotBellBlockEntity>> CARMOT_BELL_BLOCK =
            RegistryHelper.blockEntity("carmot_bell", () -> BlockEntityType.Builder.of(CarmotBellBlockEntity::new, MythicBlocks.CARMOT_BELL_BLOCK.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, RegistryHelper.id("carmot_bell").toString())));

    public static void init() {

    }


    /*
    @Override
    public boolean shouldProcessField(BlockEntityType<?> value, String identifier, Field field) {
        return BlockEntityRegistryContainer.super.shouldProcessField(value, identifier, field);
    }
     */
}
