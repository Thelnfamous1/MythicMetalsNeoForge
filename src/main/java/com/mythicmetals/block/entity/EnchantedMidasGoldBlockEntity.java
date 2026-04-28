package com.mythicmetals.block.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public class EnchantedMidasGoldBlockEntity extends BlockEntity {
    public EnchantedMidasGoldBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK, pos, state);
    }
}
