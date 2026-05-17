package com.mythicmetals.registry;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;

public class RegisterPointOfInterests {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, MythicMetals.MOD_ID);
    public static final DeferredHolder<PoiType, PoiType> CONDUIT_POWERED_BLOCK = RegistryHelper.poiType("conduit_powered_block",
            () -> new PoiType(new HashSet<>(MythicBlocks.AQUARIUM_RESONATOR.get().getStateDefinition().getPossibleStates()), 0, 1
                    )
    );

    public static void init() {
    }
}
