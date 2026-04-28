package com.mythicmetals.item;

import com.mythicmetals.entity.StarPlatinumArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.item.*;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class StarPlatinumArrowItem extends ArrowItem {

    public StarPlatinumArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new StarPlatinumArrowEntity(shooter, world, stack, shotFrom);
    }

    @Override
    public AbstractArrow createEntity(Level world, Position pos, ItemStack stack, Direction direction) {
        var entity = new StarPlatinumArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
        entity.pickupType = AbstractArrow.Pickup.ALLOWED;
        return entity;
    }
}
