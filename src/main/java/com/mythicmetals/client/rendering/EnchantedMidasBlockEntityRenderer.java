package com.mythicmetals.client.rendering;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.block.entity.EnchantedMidasGoldBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.util.RandomSource;

public class EnchantedMidasBlockEntityRenderer implements BlockEntityRenderer<EnchantedMidasGoldBlockEntity> {
    private final BlockRenderDispatcher blockRenderManager;

    public EnchantedMidasBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        blockRenderManager = ctx.getRenderManager();
    }

    @Override
    public void render(EnchantedMidasGoldBlockEntity midasBlockEntity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        matrices.push();
        blockRenderManager.renderBlock(
            MythicBlocks.MIDAS_GOLD.getStorageBlock().getDefaultState(),
            midasBlockEntity.getPos(),
            midasBlockEntity.getWorld(),
            matrices,
            ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderType.getCutoutMipped(), true, true),
            true,
            RandomSource.create());
        matrices.pop();
    }
}
