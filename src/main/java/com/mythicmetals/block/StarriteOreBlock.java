package com.mythicmetals.block;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class StarriteOreBlock extends DropExperienceBlock {
    public StarriteOreBlock(BlockBehaviour.Properties settings, UniformInt uniformIntProvider) {
        super(uniformIntProvider, settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        ClientParticles.spawnCenteredOnBlock(new DustParticleEffect(new Vector3f(UsefulSingletonForColorUtil.MetalColors.STARRITE.hsv()), 1F), world, pos, 2.0D);
    }
}
