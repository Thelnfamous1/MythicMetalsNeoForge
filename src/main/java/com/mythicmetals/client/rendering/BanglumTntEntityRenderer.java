package com.mythicmetals.client.rendering;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.entity.BanglumTntEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.renderer.texture.TextureAtlas;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Axis;

// [VanillaCopy] of the TntEntityRenderer, but renders our texture
public class BanglumTntEntityRenderer extends EntityRenderer<BanglumTntEntity> {
    private final BlockRenderDispatcher blockRenderManager;

    public BanglumTntEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
        blockRenderManager = context.getBlockRenderManager();
    }

    public void render(BanglumTntEntity banglumTnt, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0, 0.5, 0.0);
        int j = banglumTnt.getFuse();
        if ((float) j - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float) j - g + 1.0F) / 10.0F;
            h = Mth.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float k = 1.0F + h * 0.3F;
            matrixStack.scale(k, k, k);
        }

        matrixStack.multiply(Axis.POSITIVE_Y.rotationDegrees(-90.0F));
        matrixStack.translate(-0.5, -0.5, 0.5);
        matrixStack.multiply(Axis.POSITIVE_Y.rotationDegrees(90.0F));
        TntMinecartEntityRenderer.renderFlashingBlock(blockRenderManager, MythicBlocks.BANGLUM_TNT_BLOCK.getDefaultState(), matrixStack, vertexConsumerProvider, i, j / 5 % 2 == 0);
        matrixStack.pop();
        super.render(banglumTnt, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTexture(BanglumTntEntity entity) {
        return TextureAtlas.BLOCK_ATLAS_TEXTURE;

    }

}
