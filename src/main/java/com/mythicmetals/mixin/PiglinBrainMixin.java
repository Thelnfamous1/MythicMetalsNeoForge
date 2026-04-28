// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.mythicmetals.mixin;

import com.mythicmetals.armor.MythicArmorMaterials;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.MythicLootOps;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.item.*;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {

    @Unique
    private static ItemStack mythicmetals$cachedBarterItem;

    @Inject(method = "isBarterCurrency", at = @At("HEAD"), cancellable = true)
    private static void acceptMidasGold(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(MythicItems.MIDAS_GOLD.getIngot())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isWearingGold", at = @At("HEAD"), cancellable = true)
    private static void checkForMidasGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        for (ItemStack itemStack : entity.getArmorItems()) {
            Item item = itemStack.getItem();
            if (item instanceof ArmorItem armorItem && armorItem.getMaterial().value() == MythicArmorMaterials.MIDAS_GOLD) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "stopHoldingOffHandItem", at = @At("HEAD"))
    private static void mythicmetals$grabBarteredItem(Piglin piglin, boolean barter, CallbackInfo ci) {
        mythicmetals$cachedBarterItem = piglin.getOffHandStack();
    }

    @ModifyVariable(method = "getBarterResponseItems", at = @At(value = "LOAD"))
    private static LootTable giveLootForMidasGold(LootTable table, Piglin piglin) {
        if (mythicmetals$cachedBarterItem.isOf(MythicItems.MIDAS_GOLD.getIngot()) && piglin.getWorld().getServer() != null) {
            return piglin.getWorld().getServer().getReloadableRegistries().getLootTable(ResourceKey.of(Registries.LOOT_TABLE, MythicLootOps.BETTER_PIGLIN_BARTERING));
        }
        return table;
    }

}
