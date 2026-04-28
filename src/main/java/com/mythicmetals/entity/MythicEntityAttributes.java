package com.mythicmetals.entity;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.core.Holder;

public class MythicEntityAttributes {
    public static final Holder<RangedAttribute> CARMOT_SHIELD = RegistryHelper.entityAttribute("carmot_shield", new ClampedEntityAttribute("attribute.name.generic.mythicmetals.carmot_shield", 0, 0, 2048).setTracked(true));
    public static final Holder<RangedAttribute> ELYTRA_ROCKET_SPEED = RegistryHelper.entityAttribute("elytra_rocket_speed", new ClampedEntityAttribute("attribute.name.generic.mythicmetals.elytra_rocket_speed", 1, 0, 1024).setTracked(true));
    public static final Holder<RangedAttribute> FIRE_VULNERABILITY = RegistryHelper.entityAttribute("fire_vulnerability", new ClampedEntityAttribute("attribute.name.generic.mythicmetals.fire_vulnerability", 0, 0, 2048).setTracked(true));

    public static void init() {
    }
}
