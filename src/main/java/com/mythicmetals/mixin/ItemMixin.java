// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.data.MythicTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.item.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mythicmetals.component.PrometheumComponent.createOvergrownModifier;
import static com.mythicmetals.component.PrometheumComponent.createOvergrownToughnessModifier;

// TODO(Ravel): can not resolve target class Item
// TODO(Ravel): can not resolve target class Item
// TODO(Ravel): can not resolve target class Item
@Mixin(Item.class)
public abstract class ItemMixin {

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "postProcessComponents", at = @At("HEAD"))
    private void mythicmetals$dynamicAttributeHandler(ItemStack stack, CallbackInfo ci) {
        if (!stack.isIn(MythicTags.AUTO_REPAIR)) return;
        if (!stack.contains(DataComponents.ATTRIBUTE_MODIFIERS)) return;
        var prometheumComponent = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);

        // Handle Overgrown modifiers
        // Armor gets armor and toughness. Anything else gets extra damage
        if (prometheumComponent.isOvergrown()) {
            if (stack.getItem() instanceof ArmorItem item) {
                var attributeComponent = item.getAttributeModifiers();
                var changedComponent = attributeComponent
                    .with(Attributes.GENERIC_ARMOR, createOvergrownModifier(stack, 1, item.getSlotType()), EquipmentSlotGroup.forEquipmentSlot(item.getSlotType()))
                    .with(Attributes.GENERIC_ARMOR_TOUGHNESS, createOvergrownToughnessModifier(stack, 0), EquipmentSlotGroup.forEquipmentSlot(item.getSlotType()));
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
            }
            else if (stack.contains(DataComponents.ATTRIBUTE_MODIFIERS)) {
                var attributeComponent = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
                var modifier = createOvergrownModifier(stack, 0);
                var changedComponent = attributeComponent.with(Attributes.GENERIC_ATTACK_DAMAGE, modifier, EquipmentSlotGroup.MAINHAND);
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, changedComponent);
            }
        }
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void mythicmetals$inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (world.isClient()) return;

        if (stack.contains(MythicDataComponents.PROMETHEUM)) {
            PrometheumComponent.tickAutoRepair(stack, world);
        }
    }
}
