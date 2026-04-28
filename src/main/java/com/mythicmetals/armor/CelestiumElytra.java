package com.mythicmetals.armor;

import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.gameevent.GameEvent;

public class CelestiumElytra extends ElytraItem implements FabricElytraItem {
    public CelestiumElytra(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MythicItems.CELESTIUM.getIngot());
    }

    @Override
    public Holder<SoundEvent> getEquipSound() {
        return BuiltInRegistries.SOUND_EVENT.getEntry(RegisterSounds.EQUIP_CELESTIUM_ELYTRA);
    }

    public static boolean isWearing(LivingEntity entity) {
        var stack = entity.getEquippedStack(EquipmentSlot.CHEST);
        return stack.isOf(MythicArmor.CELESTIUM_ELYTRA) && isUsable(stack);
    }

    @Override
    public void doVanillaElytraTick(LivingEntity entity, ItemStack chestStack) {
        int nextRoll = entity.getFallFlyingTicks() + 1;

        if (!entity.getWorld().isClient && nextRoll % 10 == 0) {
            if ((nextRoll / 10) % 4 == 0) {
                chestStack.damage(1, entity, EquipmentSlot.CHEST);
            }

            entity.emitGameEvent(GameEvent.ELYTRA_GLIDE);
        }
    }

    public static ItemAttributeModifiers createDefaultAttributes() {
        var builder = ItemAttributeModifiers.builder();
        var armor = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_armor_protection"), 5.0F, AttributeModifier.Operation.ADD_VALUE);
        var toughness = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_armor_toughness"), 3.0F, AttributeModifier.Operation.ADD_VALUE);
        var speed = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_speed_bonus"), 0.08F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        var rocketSpeedBonus = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_rocket_speed_bonus"), 0.20F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        builder.add(EntityAttributes.GENERIC_ARMOR, armor, EquipmentSlotGroup.CHEST);
        builder.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, toughness, EquipmentSlotGroup.CHEST);
        builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed, EquipmentSlotGroup.CHEST);
        builder.add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED, rocketSpeedBonus, EquipmentSlotGroup.CHEST);
        return builder.build();
    }
}
