package com.mythicmetals.block.entity;

import com.mythicmetals.block.CarmotBellBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class CarmotBellBlockEntity extends BlockEntity {

    protected int cooldown = 0;

    public CarmotBellBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.CARMOT_BELL_BLOCK.get(), pos, state);
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, CarmotBellBlockEntity be) {
        if (be.cooldown > 0) {
            be.cooldown--;
        }
    }

    public boolean canBeUsed() {
        return cooldown == 0;
    }

    public void setChanged() {
        cooldown = CarmotBellBlock.COOLDOWN;
        setChanged();
    }
}
