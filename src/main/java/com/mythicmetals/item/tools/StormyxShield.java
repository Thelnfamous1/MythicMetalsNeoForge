package com.mythicmetals.item.tools;

import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.level.Level;

import static com.mythicmetals.component.MythicDataComponents.WAS_USED;

public class StormyxShield extends ShieldItem {

    public static final int MAGIC_DAMAGE_REDUCTION = 2;
    public static final ProjectileDeflection STORMYX_SHIELD_DEFLECTOR = (projectile, hitEntity, random) -> {
        // Shulker bullet handling
        if (projectile instanceof ShulkerBullet bullet) {
            bullet.damage(bullet.getWorld().getDamageSources().generic(), 1.0F);
            return;
        }

        // If the projectile is simply too fast then it isn't deflected. It can still be blocked by the shield itself
        if (projectile.getVelocity().length() <= 30.0) {
            float f = 170.0F + random.nextFloat() * 20.0F;
            projectile.setVelocity(projectile.getVelocity().multiply(-0.5));
            projectile.setYaw(projectile.getYaw() + f);
            projectile.prevYaw += f;
            projectile.velocityDirty = true;
        }
    };

    public StormyxShield(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        disableShield(stack, world, user);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public void usageTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        if (remainingUseTicks % 40 == 1) {
            WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_MAINTAIN, SoundSource.AMBIENT, 1.0F, 1.5F);
            stack.damage(1, user, LivingEntity.getSlotForHand(user.getActiveHand()));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        var stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        stack.set(WAS_USED, true);
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_BEGIN, SoundSource.AMBIENT, 1.0F, 1.5F);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MythicItems.STORMYX.getIngot());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Player entity, int slot, boolean selected) {
        if (entity instanceof Player player && stack.contains(WAS_USED)) {
            if (!player.getMainHandStack().equals(stack) && !player.getOffHandStack().equals(stack)) {
                stack.remove(WAS_USED);
                finishUsing(stack, world, player);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, Level world, LivingEntity user) {
        return disableShield(stack, world, user);
    }

    private ItemStack disableShield(ItemStack stack, Level world, LivingEntity user) {
        if (!world.isClient && user instanceof Player player) {
            stack.remove(WAS_USED);
            player.getItemCooldownManager().set(stack.getItem(), 160);
        }
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_END, SoundSource.AMBIENT, 0.9F, 1.5F);
        return stack;
    }

    public static ItemAttributeModifiers createStormyxShieldAttributes() {
        var modifier = new EntityAttributeModifier(RegistryHelper.id("stormyx_shield_magic_protection"), MAGIC_DAMAGE_REDUCTION, AttributeModifier.Operation.ADD_VALUE);
        return ItemAttributeModifiers.builder()
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, EquipmentSlotGroup.MAINHAND)
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, EquipmentSlotGroup.OFFHAND)
            .build();
    }

    // Don't update the item in hand if durability is repaired
    // Might affect mending as a side effect
    @Override
    public boolean allowComponentsUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamage() == newStack.getDamage();
    }
}
