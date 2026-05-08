package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static com.mythicmetals.item.tools.StormyxShield.STORMYX_SHIELD_DEFLECTOR;

// TODO(Ravel): can not resolve target class Entity
// TODO(Ravel): can not resolve target class Entity
// TODO(Ravel): can not resolve target class Entity
@Mixin(Entity.class)
public class EntityMixin {

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @ModifyReturnValue(method = "deflection", at = @At("RETURN"))
    private ProjectileDeflection mythicmetals$reflectProjectilesWithStormyxShield(ProjectileDeflection original) {
        var entity = (Entity) (Object) this;
        if (!(entity instanceof LivingEntity living)) return original;
        if (living.getUseItem().getItem().equals(MythicTools.STORMYX_SHIELD)) return STORMYX_SHIELD_DEFLECTOR;
        return original;
    }
}
