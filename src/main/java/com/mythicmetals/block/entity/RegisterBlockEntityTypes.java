package com.mythicmetals.block.entity;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import java.lang.reflect.Field;

public class RegisterBlockEntityTypes implements BlockEntityRegistryContainer {

    public static final BlockEntityType<AquariumResonatorBlockEntity> AQUARIUM_RESONATOR =
            BlockEntityType.Builder.of(AquariumResonatorBlockEntity::new, MythicBlocks.AQUARIUM_RESONATOR.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, RegistryHelper.id("aquarium_resonator").toString()));

    public static final BlockEntityType<EnchantedMidasGoldBlockEntity> ENCHANTED_MIDAS_GOLD_BLOCK =
        BlockEntityType.Builder.of(EnchantedMidasGoldBlockEntity::new, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, RegistryHelper.id("enchanted_midas_gold_block").toString()));

    public static final BlockEntityType<CarmotBellBlockEntity> CARMOT_BELL_BLOCK =
        BlockEntityType.Builder.of(CarmotBellBlockEntity::new, MythicBlocks.CARMOT_BELL_BLOCK.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, RegistryHelper.id("carmot_bell").toString()));

    @Override
    public boolean shouldProcessField(BlockEntityType<?> value, String identifier, Field field) {
        return BlockEntityRegistryContainer.super.shouldProcessField(value, identifier, field);
    }
}
