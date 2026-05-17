package com.mythicmetals.item.tools;

//import net.fabricmc.fabric.api.item.v1.FabricItem;
//import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.common.extensions.IItemExtension;

/**
 * Used to handle Auto Repair behavior regarding more gracefully
 */
public interface AutoRepairable /*extends FabricItem*/ extends IItemExtension {

    // Don't interrupt mining if durability is repaired
    // Might affect mending as a side effect
    /*
    @Override
    default boolean allowContinuingBlockBreaking(Player player, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamageValue() != newStack.getDamageValue();
    }
     */

    @Override
    default boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamageValue() == newStack.getDamageValue();
    }

    // Don't update the item in hand if durability is repaired
    // Might affect mending as a side effect
    /*
    @Override
    default boolean allowComponentsUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamageValue() == newStack.getDamageValue();
    }
     */

    @Override
    default boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (slotChanged) {
            return true;
        }

        return oldStack.getDamageValue() != newStack.getDamageValue();
    }
}
