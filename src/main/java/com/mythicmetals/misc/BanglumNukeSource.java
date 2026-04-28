package com.mythicmetals.misc;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class BanglumNukeSource extends DamageSource {
    public BanglumNukeSource(Holder<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
        super(type, source, attacker);
    }

    @Override
    public Component getDeathMessage(LivingEntity killed) {
        if (this.getAttacker() != null) {
            return Component.translatable("death.attack.banglum_nuke.player", killed.getDisplayName(), this.getAttacker().getDisplayName());
        }
        return super.getDeathMessage(killed);
    }
}
