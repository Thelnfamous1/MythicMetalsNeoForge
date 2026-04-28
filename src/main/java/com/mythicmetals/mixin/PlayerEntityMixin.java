package com.mythicmetals.mixin;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.tools.HammerBase;
import com.mythicmetals.misc.IsAttackCritical;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.stats.Stat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO(Ravel): can not resolve target class PlayerEntity
// TODO(Ravel): can not resolve target class PlayerEntity
// TODO(Ravel): can not resolve target class PlayerEntity
@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IsAttackCritical {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Unique
    public boolean mythicmetals$isCritical = false;

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract PlayerInventory getInventory();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    public abstract void incrementStat(Stat<?> stat);

    // TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
// TODO(Ravel): Could not determine a single target
    @Shadow
    @Final
    private ItemCooldownManager itemCooldownManager;

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void slowBreak(BlockState blockState, CallbackInfoReturnable<Float> cir) {
        var mainHandStack = getInventory().getMainHandStack();
        float speedMod = 1.0f;

        // Don't do any special handling if you are not holding a tool
        if (mainHandStack.isEmpty()) return;

        // Slow down mining MM ores if you are using an item without a high enough mining level
        if (blockState.isIn(MythicTags.MYTHIC_ORES) && !mainHandStack.isSuitableFor(blockState)) {
            if (mainHandStack.hasEnchantments() && mainHandStack.getEnchantments().getEnchantments().iterator().next().equals(Enchantments.EFFICIENCY)) {
                speedMod *= 0.01f;
            } else {
                speedMod *= 0.3f;
            }

        }

        // Slow down Hammers
        if (mainHandStack.getItem() instanceof HammerBase) {
            speedMod *= 0.9f;
        }

        if (speedMod < 1.0f) {
            var speed = cir.getReturnValue();
            cir.setReturnValue(speed * speedMod);
        }

    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "tick", at = @At("TAIL"))
    private void tickCarmotShield(CallbackInfo ci) {
        getData(MythicMetals.CARMOT_SHIELD).tickShield();
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @ModifyVariable(
        method = "applyDamage",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;applyArmorToDamage(Lnet/minecraft/world/damagesource/DamageSource;F)F",
            shift = At.Shift.BY, by = -2),
        ordinal = 0,
        argsOnly = true)
    public float carmotShieldCancel(float amount) {
        var shield = getData(MythicMetals.CARMOT_SHIELD);
        if (shield.getMaxHealth() > 0) {
            float health = shield.shieldHealth;
            shield.damageShield(amount);
            return amount > health ? amount - health : 0;

        }

        return amount;
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "attack", at = @At("HEAD"))
    private void setMythicmetals$resetCritical(Entity target, CallbackInfo ci) {
        mythicmetals$setCritical(false);
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;crit(Lnet/minecraft/world/entity/Entity;)V"))
    private void mythicmetals$captureCritical(CallbackInfo ci) {
        mythicmetals$setCritical(true);
    }

    @Override
    public void mythicmetals$setCritical(boolean isCritical) {
        mythicmetals$isCritical = isCritical;
    }

    @Override
    public boolean mythicmetals$isCritical() {
        return mythicmetals$isCritical;
    }
}
