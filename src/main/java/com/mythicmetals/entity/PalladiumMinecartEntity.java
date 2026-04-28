package com.mythicmetals.entity;

import com.mythicmetals.MythicMetals;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;

public class PalladiumMinecartEntity extends Minecart {

    public PalladiumMinecartEntity(EntityType<?> entityType, Level world) {
        super(entityType, world);
    }

    public PalladiumMinecartEntity(Level world, double x, double y, double z) {
        this(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    public EntityType getMinecartType() {
        return MythicMetals.PALLADIUM_MINECART;
    }
}
