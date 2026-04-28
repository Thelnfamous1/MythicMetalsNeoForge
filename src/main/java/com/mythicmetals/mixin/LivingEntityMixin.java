package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.entity.CombustionCooldown;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.misc.WasSpawnedFromCreeper;
import com.mythicmetals.registry.RegisterCriteria;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.entity.effect.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mythicmetals.entity.MythicEntityAttributes.FIRE_VULNERABILITY;

// TODO(Ravel): can not resolve target class LivingEntity
// TODO(Ravel): can not resolve target class LivingEntity
// TODO(Ravel): can not resolve target class LivingEntity
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Player {
    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean canFreeze();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract int getArmor();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    private @Nullable LivingEntity attacker;

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean canHaveStatusEffect(StatusEffectInstance effect);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract ItemStack getStackInHand(InteractionHand hand);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract void stopRiding();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean hasStatusEffect(Holder<StatusEffect> effect);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract double getAttributeValue(Holder<EntityAttribute> attribute);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract @Nullable StatusEffectInstance getStatusEffect(Holder<StatusEffect> effect);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract boolean removeStatusEffect(Holder<StatusEffect> effect);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract AttributeContainer getAttributes();

    public LivingEntityMixin(EntityType<?> type, ServerLevel world) {
        super(type, world);
    }

    @Unique
    Random r = new Random();

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
    private static void mythicmetals$addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(MythicEntityAttributes.CARMOT_SHIELD);
        info.getReturnValue().add(FIRE_VULNERABILITY);
        info.getReturnValue().add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z"))
    private boolean mythicmetals$bypassFireResistance(boolean original) {
        // We respect Fire Invulnerability, but not Fire Resistance
        // original = source.isFire() && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)
        return original && !(this.getAttributeValue(FIRE_VULNERABILITY) > 0);
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
/**
     * Increase fire damage taken by 1 for each point of Fire Vulnerability
     * Fire Resistance halves this, although you will still take fire damage this way
     */
    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    private float mythicmetals$changeFireDamage(float original, DamageSource source) {
        if (!this.getAttributes().hasAttribute(FIRE_VULNERABILITY) || !source.isIn(DamageTypeTags.IS_FIRE)) {
            return original;
        }

        float baseDamage = (float) this.getAttributeValue(FIRE_VULNERABILITY);
        float modifier = this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE) ? Math.min(Mth.floor((baseDamage / 2.0f)), 1) : baseDamage;
        return original + modifier;
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "tick", at = @At("HEAD"))
    private void mythicmetals$tick(CallbackInfo ci) {
        if (!getWorld().isClient()) {
            mythicmetals$tickCombustion();
        }
        mythicmetals$palladiumParticles();
        mythicmetals$addArmorEffects();
    }

    @Unique
    private void mythicmetals$tickCombustion() {
        var component = getData(MythicMetals.COMBUSTION_COOLDOWN);
        component.tickCooldown();
        mythicmetals$handleCombustion(component);
    }

    @Unique
    private void mythicmetals$handleCombustion(CombustionCooldown component) {
        var entry = BuiltInRegistries.STATUS_EFFECT.getEntry(MythicStatusEffects.HEAT);
        if (this.isOnFire() && this.hasStatusEffect(BuiltInRegistries.STATUS_EFFECT.getEntry(MythicStatusEffects.HEAT)) && component.isCombustible()) {
            var effect = this.getStatusEffect(entry);
            if (effect != null) {
                int level = effect.getAmplifier();
                int duration = effect.getDuration();
                var multiplier = new AtomicInteger(effect.getDuration());
                this.removeStatusEffect(entry);

                MythicParticleSystem.COMBUSTION_EXPLOSION.spawn(getWorld(), this.getPos());

                if (this.attacker != null && this.attacker.getMainHandStack() != null) {
                    var stack = this.attacker.getMainHandStack();
                    stack.getEnchantments().getEnchantments().forEach(enchantmentRegistryEntry -> {
                        if (enchantmentRegistryEntry.isIn(EnchantmentTags.SMELTS_LOOT)) {
                            multiplier.addAndGet(1);
                        }
                    });
                }

                this.addStatusEffect(new StatusEffectInstance(BuiltInRegistries.STATUS_EFFECT.getEntry(MythicStatusEffects.COMBUSTION), multiplier.get() + 40, Math.max(Mth.floor(level / 2.0f), 0), false, true));

                this.setOnFireForTicks((duration * multiplier.get()) + 40);
                component.setCooldown(1800);
            }

        }
    }

    @Unique
    private void mythicmetals$addArmorEffects() {
        for (ItemStack armorStack : getArmorItems()) {
            // Turns out, this bug was in Minecraft itself
            // It only took a couple of years to find, and it was re-producible in vanilla context
            if (armorStack.isEmpty()) continue; // Don't get the item for an empty stack
            if (armorStack.getItem() == null) {
                MythicMetals.LOGGER.error("An ItemStack was somehow marked as not empty, but it doesn't contain an item.");
                MythicMetals.LOGGER.error("This is not caused by Mythic Metals, and it could potentially crash!");
                MythicMetals.LOGGER.error("Skipping the Armor Item query");
                continue;
            }

            if (MythicArmor.CARMOT.isInArmorSet(armorStack)) {
                mythicmetals$carmotParticle();
            }

            if (MythicArmor.COPPER.isInArmorSet(armorStack) && getWorld().isThundering()) {
                Vec3 playerPos = this.getPos();
                boolean isConductive = playerPos.y == getWorld().getTopY(Heightmap.Types.WORLD_SURFACE, (int) playerPos.x, (int) playerPos.z);
                int rng = r.nextInt(60000);

                // Display particles on client
                mythicmetals$copperParticle();

                // Randomly strike the player with lightning when conductive
                if (rng == 666 & isConductive) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(getWorld());
                    if (lightningEntity != null) {
                        lightningEntity.copyPositionAndRotation(this);
                        getWorld().spawnEntity(lightningEntity);
                        this.damage(getWorld().getDamageSources().lightningBolt(), 10);
                    }
                }
            }
        }
    }

    @Unique
    private void mythicmetals$carmotParticle() {
        if (!this.getWorld().isClient()) return;
        Vec3 velocity = this.getVelocity();

        if (this.isPlayer() && this.getData(MythicMetals.CARMOT_SHIELD).shieldHealth == 0) {
            return; // If you are a player, and your shield ran out, do not display particles
        }

        // Particle trail if the entity is moving
        if (velocity.length() >= 0.1 && r.nextInt(10) < 1) {
            MythicParticleSystem.CARMOT_TRAIL.spawn(getWorld(), this.getPos());
        }
    }

    @Unique
    private void mythicmetals$copperParticle() {
        if (this.getWorld().isClient() && r.nextInt(40) < 1) {
            MythicParticleSystem.COPPER_SPARK.spawn(getWorld(), this.getPos().add(0, 1, 0));
        }
    }

    @Unique
    private void mythicmetals$palladiumParticles() {
        var heatEntry = BuiltInRegistries.STATUS_EFFECT.getEntry(MythicStatusEffects.HEAT);
        if (this.hasStatusEffect(heatEntry)) {
            var status = this.getStatusEffect(heatEntry);
            if (status == null || status.getAmplifier() < 3) return;

            Vec3 velocity = this.getVelocity();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.SMOKING_PALLADIUM_PARTICLE.spawn(getWorld(), this.getPos().add(0, 0.25, 0));
            }
        }

        if (this.hasStatusEffect(BuiltInRegistries.STATUS_EFFECT.getEntry(MythicStatusEffects.COMBUSTION))) {
            Vec3 velocity = this.getVelocity();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.OVERENGINEERED_PALLADIUM_PARTICLE.spawn(getWorld(), this.getPos().add(0, 0.25, 0));
            }
        }
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
/**
     * Bonus advancement if you combust yourself via a creeper. Good job.
     */
    @Inject(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"))
    private void mythicmetals$grantAdvancementOnStatusEffectFromCreepers(StatusEffectInstance effect, Player source, CallbackInfoReturnable<Boolean> cir) {
        if (this.getWorld().isClient() || source == null || !this.canHaveStatusEffect(effect)) return;
        if (effect.getEffectType().value().equals(MythicStatusEffects.COMBUSTION) && this.isPlayer()) {
            if (source instanceof AreaEffectCloudEntity cloudEntity && ((WasSpawnedFromCreeper) cloudEntity).mythicmetals$isSpawnedFromCreeper()) {
                //noinspection ConstantConditions
                RegisterCriteria.RECEIVED_COMBUSTION_FROM_CREEPER.trigger(((ServerPlayer) (Object) this));
            }
        }
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Environment(EnvType.CLIENT)
    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;Z)V", at = @At("HEAD"), cancellable = true)
    private void mythicmetals$cancelSwingOnActiveMythrilDrill(InteractionHand hand, boolean fromServerPlayer, CallbackInfo ci) {
        if (!this.getWorld().isClient()) {
            return;
        }
        var stack = this.getStackInHand(hand);
        var camera = Minecraft.getInstance().getEntityRenderDispatcher().camera;
        // This can be null, according to #252
        if (camera == null) return;
        if (camera.isThirdPerson() && stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
            ci.cancel();
        }
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "dropEquipment", at = @At(value = "HEAD"))
    private void mythicmetals$dropMidasGold(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
        if (source.getAttacker() == null) return;
        if (source.getAttacker() instanceof Player attacker1) {
            if (MythicMetals.CONFIG.midasGold() && attacker1.getMainHandStack().isIn(MythicTags.MIDAS_TOUCH)) {
                this.dropStack(new ItemStack(MythicItems.MIDAS_GOLD.getRawOre()));
            }
        }
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "tickRiding", at = @At("HEAD"))
    private void mythicmetals$tickRiding(CallbackInfo ci) {
        if (this.hasVehicle() && this.getWorld().getTime() % 40 == 1 && this.getVehicle().getType().isIn(MythicTags.GRANTS_FIRE_RES_WHILE_RIDING)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 120));
        }
    }
}
