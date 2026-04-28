package com.mythicmetals.entity;

import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.entity.*;
import net.minecraft.entity.data.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

// [VanillaCopy]
public class RuniteArrowEntity extends AbstractArrow {
    private static final TrackedData<Integer> COLOR = DataTracker.registerData(RuniteArrowEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final ItemStack RUNITE_ARROW_STACK = new ItemStack(MythicTools.RUNITE_ARROW);

    public RuniteArrowEntity(EntityType<RuniteArrowEntity> type, Level world) {
        super(type, world);
        this.initColor();
    }

    public RuniteArrowEntity(LivingEntity shooter, Level world, @Nullable ItemStack shotFrom) {
        super(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, shooter, world, RUNITE_ARROW_STACK, shotFrom);
        this.initColor();
    }

    public RuniteArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, x, y, z, world, stack, shotFrom);
    }

    public RuniteArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, owner, world, stack, shotFrom);
        this.initColor();
    }

    private PotionContents getPotionContents() {
        return this.getItemStack().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.DEFAULT);
    }

    protected void initColor() {
        PotionContents potionContentsComponent = this.getPotionContents();
        this.dataTracker.set(COLOR, potionContentsComponent.equals(PotionContents.DEFAULT) ? -1 : potionContentsComponent.getColor());
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return RUNITE_ARROW_STACK;
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        AbstractArrow entity = this.getEffectCause();
        PotionContents potionContentsComponent = this.getPotionContents();
        if (potionContentsComponent.potion().isPresent()) {
            for (var statusEffectInstance : potionContentsComponent.potion().get().value().getEffects()) {
                target.addStatusEffect(
                    new StatusEffectInstance(
                        statusEffectInstance.getEffectType(),
                        Math.max(statusEffectInstance.mapDuration(i -> i / 8), 1),
                        statusEffectInstance.getAmplifier(),
                        statusEffectInstance.isAmbient(),
                        statusEffectInstance.shouldShowParticles()
                    ),
                    entity
                );
            }
        }

        for (MobEffectInstance statusEffectInstance : potionContentsComponent.customEffects()) {
            target.addStatusEffect(statusEffectInstance, entity);
        }
    }

    public int getColor() {
        return this.dataTracker.get(COLOR);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR, -1);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && !this.getPotionContents().equals(PotionContents.DEFAULT) && this.inGroundTime >= 600) {
            this.getWorld().sendEntityStatus(this, (byte) 0);
            this.setStack(RUNITE_ARROW_STACK);
        }
    }

    private void spawnParticles(int amount) {
        int i = this.getColor();
        if (i != -1 && amount > 0) {
            for (int j = 0; j < amount; ++j) {
                this.getWorld()
                    .addParticle(
                        ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, i), this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 0.0, 0.0, 0.0
                    );
            }
        }
    }
}
