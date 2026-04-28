package com.mythicmetals.entity;

import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.MythicDamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class StarPlatinumArrowEntity extends AbstractArrow {
    public static final ItemStack STAR_PLAT_STACK = new ItemStack(MythicTools.STAR_PLATINUM_ARROW);

    public StarPlatinumArrowEntity(LivingEntity owner, Level world, ItemStack stack, @Nullable ItemStack weapon) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, owner, world, stack, weapon);
    }

    public StarPlatinumArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, x, y, z, world, stack, shotFrom);
    }

    public StarPlatinumArrowEntity(EntityType<StarPlatinumArrowEntity> type, Level world) {
        super(type, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return STAR_PLAT_STACK;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return STAR_PLAT_STACK;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        var source = new DamageSource(
            this.getWorld().getRegistryManager().get(Registries.DAMAGE_TYPE).getEntry(MythicDamageTypes.STAR_PLATINUM_ARROW).orElseThrow(),
            this,
            getOwner());
        if (target.getType().isIn(EntityTypeTags.UNDEAD)) {
            target.addStatusEffect(new StatusEffectInstance(MobEffects.INSTANT_HEALTH, 1, 3));
        } else {
            target.damage(source, 24);
        }
    }

    @Override
    public void writeCustomDataToNbt(CompoundTag nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(CompoundTag nbt) {
        super.readCustomDataFromNbt(nbt);
    }
}
