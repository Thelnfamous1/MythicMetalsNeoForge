package com.mythicmetals.entity;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MythicEntityAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, MythicMetals.MOD_ID);
    public static final DeferredHolder<Attribute, Attribute> CARMOT_SHIELD = RegistryHelper.entityAttribute("carmot_shield", () -> new RangedAttribute("attribute.name.generic.mythicmetals.carmot_shield", 0, 0, 2048).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> ELYTRA_ROCKET_SPEED = RegistryHelper.entityAttribute("elytra_rocket_speed", () -> new RangedAttribute("attribute.name.generic.mythicmetals.elytra_rocket_speed", 1, 0, 1024).setSyncable(true));
    public static final DeferredHolder<Attribute, Attribute> FIRE_VULNERABILITY = RegistryHelper.entityAttribute("fire_vulnerability", () -> new RangedAttribute("attribute.name.generic.mythicmetals.fire_vulnerability", 0, 0, 2048).setSyncable(true));


    public static void init() {
    }
}
