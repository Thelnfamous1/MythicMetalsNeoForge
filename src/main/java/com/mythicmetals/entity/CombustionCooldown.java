package com.mythicmetals.entity;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

/**
 * Component used to prevent entities from constantly receiving the {@link com.mythicmetals.effects.CombustingStatusEffect}
 */
public class CombustionCooldown implements INBTSerializable<CompoundTag> {
    private int cooldown;

    public CombustionCooldown(LivingEntity entity) {
        cooldown = 0;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryLookup, CompoundTag tag) {
        cooldown = tag.getInt("cooldown");
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider registryLookup) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("cooldown", cooldown);
        return tag;
    }

    public void setCooldown(int ticks) {
        cooldown = ticks;
    }

    public boolean isCombustible() {
        return !(cooldown > 0);
    }

    public void tickCooldown() {
        if (cooldown > 0) {
            cooldown = Mth.clamp(cooldown - 1, 0, Integer.MAX_VALUE);
        }
    }

    public static class SyncHandler implements AttachmentSyncHandler<CombustionCooldown> {

        @Override
        public void write(RegistryFriendlyByteBuf registryFriendlyByteBuf, CombustionCooldown combustionCooldown, boolean b) {
            registryFriendlyByteBuf.writeInt(combustionCooldown.cooldown);
        }

        @Override
        public @Nullable CombustionCooldown read(IAttachmentHolder iAttachmentHolder, RegistryFriendlyByteBuf registryFriendlyByteBuf, @Nullable CombustionCooldown combustionCooldown) {
            if(!(iAttachmentHolder instanceof LivingEntity livingEntity)) return null;
            CombustionCooldown readCombustionCooldown = new CombustionCooldown(livingEntity);
            readCombustionCooldown.cooldown = registryFriendlyByteBuf.readInt();
            return readCombustionCooldown;
        }
    }
}
