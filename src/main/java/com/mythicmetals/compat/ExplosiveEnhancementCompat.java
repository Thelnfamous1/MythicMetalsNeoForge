package com.mythicmetals.compat;

import dev.xylonity.explosiveenhancement.ExplosiveHandler;
import net.minecraft.world.level.Level;
//import net.superkat.explosiveenhancement.api.ExplosiveApi;

public class ExplosiveEnhancementCompat {
    public static void spawnParticles(Level world, double x, double y, double z, float power) {
        ExplosiveHandler.spawnParticles(world, x, y, z, power, false, true, true);
    }
}
