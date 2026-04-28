package com.mythicmetals.item;

import com.mythicmetals.entity.RuniteArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.item.*;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class RuniteArrowItem extends ArrowItem {

    public RuniteArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new RuniteArrowEntity(world, shooter, stack, shotFrom);
    }

    @Override
    public AbstractArrow createEntity(Level world, Position pos, ItemStack stack, Direction direction) {
        var entity = new RuniteArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
        entity.pickupType = AbstractArrow.Pickup.ALLOWED;
        return entity;
    }

    @Override
    public ProjectileItem.Settings getProjectileSettings() {
        return new ProjectileItem.Settings.Builder()
            .power(1.4f)
            .uncertainty(5.0f)
            .build();
    }
}
