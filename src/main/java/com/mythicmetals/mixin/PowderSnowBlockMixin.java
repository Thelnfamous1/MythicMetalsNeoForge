// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.mythicmetals.mixin;

import com.mythicmetals.armor.MythicArmor;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {

    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$palladiumCanWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity && ((LivingEntity) entity).getEquippedStack(EquipmentSlot.FEET).isOf(MythicArmor.PALLADIUM.getBoots())) {
            cir.setReturnValue(true);
        }
    }
}
