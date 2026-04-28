package com.mythicmetals.item.tools;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.*;
import net.minecraft.world.level.Level;

// TODO(Ravel): ambiguous static import, members with name ADD_MULTIPLIED_BASE have different new names
//
// TODO(Ravel): ambiguous static import, members with name ADD_MULTIPLIED_BASE have different new names
//
// TODO(Ravel): ambiguous static import, members with name ADD_MULTIPLIED_BASE have different new names
//
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_MULTIPLIED_BASE;

public class TidesingerToolSet extends ToolSet {
    public TidesingerToolSet(Tier material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

    @Override
    protected SwordItem makeSword(Tier material, int damage, float speed, Item.Properties settings) {
        return new TidesingerSword(material, settings.attributes(createAttributeModifiers(material, damage, speed)));
    }

    @Override
    protected AxeItem makeAxe(Tier material, int damage, float speed, Item.Properties settings) {
        return new TidesingerAxe(material, settings.attributes(createAttributeModifiers(material, damage, speed)));
    }

    public static class TidesingerSword extends SwordItem implements RiptideTool {

        public TidesingerSword(Tier material, Item.Properties settings) {
            super(material, settings);
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
            return activateRiptide(user, hand);
        }

        @Override
        public UseAnim getUseAnimation(ItemStack stack) {
            return UseAnim.SPEAR;
        }

        @Override
        public int getUseDuration(ItemStack stack, LivingEntity user) {
            return RiptideTool.MAX_USE_TIME;
        }


        @Override
        public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
            performRiptide(stack, world, user, remainingUseTicks);
        }
    }

    public static class TidesingerAxe extends AxeItem implements RiptideTool {

        public TidesingerAxe(Tier material, Properties settings) {
            super(material, settings);
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
            return activateRiptide(user, hand);
        }

        @Override
        public UseAnim getUseAnimation(ItemStack stack) {
            return UseAnim.SPEAR;
        }

        @Override
        public int getUseDuration(ItemStack stack, LivingEntity user) {
            return RiptideTool.MAX_USE_TIME;
        }

        @Override
        public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
            performRiptide(stack, world, user, remainingUseTicks);
        }

        @Override
        public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        }
    }

    @Override
    public ItemAttributeModifiers.Builder createAttributeBuilder(Tier material, double damage, float speed) {
        return super.createAttributeBuilder(material, damage, speed).add(Attributes.SUBMERGED_MINING_SPEED,
            new AttributeModifier(RegistryHelper.id("tidesinger_tool_bonus"), 1.5f, ADD_MULTIPLIED_BASE),
            EquipmentSlotGroup.MAINHAND
        );
    }
}
