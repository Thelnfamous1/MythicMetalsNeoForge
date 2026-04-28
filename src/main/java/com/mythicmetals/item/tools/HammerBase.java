package com.mythicmetals.item.tools;

import net.minecraft.item.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class HammerBase extends PickaxeItem {

    private final int depth;

    public HammerBase(ToolMaterial material, Settings settings, int depth) {
        super(material, settings);
        this.depth = depth;
    }

    public boolean canBreak(ItemStack stack, BlockGetter view, BlockPos pos) {
        return super.isCorrectForDrops(stack, view.getBlockState(pos));
    }

    public int getDepth() {
        return depth;
    }
}
