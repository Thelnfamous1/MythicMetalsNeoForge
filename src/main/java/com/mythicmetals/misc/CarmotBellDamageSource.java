package com.mythicmetals.misc;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CarmotBellDamageSource extends DamageSource {
    public CarmotBellDamageSource(Holder<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
        super(type, source, attacker);
    }

    @Override
    public Component getDeathMessage(LivingEntity killed) {
        if (this.getAttacker() != null) {
            return Component.translatable("death.attack.carmot_bell.player", killed.getDisplayName(), this.getAttacker().getDisplayName());
        }
        return Component.translatable("death.attack.carmot_bell", killed.getDisplayName());
    }

    public static CarmotBellDamageSource of(Level world, @Nullable LivingEntity attacker) {
        return new CarmotBellDamageSource(world.getRegistryManager().get(Registries.DAMAGE_TYPE).getEntry(MythicDamageTypes.CARMOT_BELL).orElseThrow(), null, attacker);
    }
}
