package com.mythicmetals.mixin;

import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.models.StarPlatCloakModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.*;
import net.minecraft.client.model.PlayerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.util.Mth;
import com.mojang.math.Axis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO(Ravel): can not resolve target class CapeFeatureRenderer
// TODO(Ravel): can not resolve target class CapeFeatureRenderer
// TODO(Ravel): can not resolve target class CapeFeatureRenderer
@Environment(EnvType.CLIENT)
@Mixin(CapeFeatureRenderer.class)
public abstract class CapeFeatureRendererMixin extends FeatureRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public CapeFeatureRendererMixin(FeatureRendererContext<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> ctx) {
        super(ctx);
    }

    // TODO(Ravel): no target class
// TODO(Ravel): no target class
// TODO(Ravel): no target class
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/network/AbstractClientPlayerEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    public void render(PoseStack ms, VertexConsumerProvider vertices, int light, AbstractClientPlayer player, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (!player.isPartVisible(PlayerModelPart.CAPE) || player.getSkinTextures().capeTexture() != null) return;
        if (!LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.invoker().allowCapeRender(player))
            return;

        // Custom Hallowed Cape when no other cape is present
        if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == MythicArmor.HALLOWED.getChestplate().asItem()) {
            mythicmetals$renderHallowedCape(ms, vertices, light, player, f, g, h, j, k, l);
            ci.cancel();
            return;
        }
        // Custom Star Platinum Cloak when no other cape is present
        if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == MythicArmor.STAR_PLATINUM.getChestplate().asItem()) {
            mythicmetals$renderStarPlatCape(ms, vertices, light, player, f, g, h, j, k, l);
            ci.cancel();
        }
    }

    @Unique
    private void mythicmetals$renderStarPlatCape(PoseStack ms, VertexConsumerProvider vertices, int light, AbstractClientPlayer player, float f, float g, float h, float j, float k, float l) {
        double x = Mth.lerpAngleDegrees(h, (float) player.prevCapeX, (float) player.capeX)
            - Mth.lerpAngleDegrees(h, (float) player.prevX, (float) player.getX());
        double y = Mth.lerpAngleDegrees(h, (float) player.prevCapeY, (float) player.capeY)
            - Mth.lerpAngleDegrees(h, (float) player.prevY, (float) player.getY());
        double z = Mth.lerpAngleDegrees(h, (float) player.prevCapeZ, (float) player.capeZ)
            - Mth.lerpAngleDegrees(h, (float) player.prevZ, (float) player.getZ());
        float yaw = player.prevBodyYaw + (player.bodyYaw - player.prevBodyYaw);
        double o = Mth.sin(yaw * (float) (Math.PI / 180.0));
        double p = -Mth.cos(yaw * (float) (Math.PI / 180.0));
        float q = (float) y * 10.0F;
        q = Mth.clamp(q, -6.0F, 32.0F);
        float r = (float) (x * o + z * p) * 100.0F;
        r = Mth.clamp(r, 0.0F, 150.0F);
        float capeZOffset = (float) (x * p - z * o) * 100.0F;
        capeZOffset = Mth.clamp(capeZOffset, -20.0F, 20.0F);
        if (r < 0.0F) {
            r = 0.0F;
        }


        float t = Mth.lerp(h, player.prevStrideDistance, player.strideDistance);
        q += Mth.sin(Mth.lerp(h, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;

        if (player.isInSneakingPose()) {
            q += 25.0F;
        }

        float backCapeRotation = Mth.clamp(6.0F + r / 2.0F + q, -30, 60);
        VertexConsumer vertexConsumer = vertices.getBuffer(RenderLayer.getEntitySolid(MythicModelHandler.STAR_PLATINUM_CLOAK));

        // Transform and render the custom cape
        ms.push();
        ms.translate(0, -0.05, 0.0); // Push up and backwards, then rotate
        ms.multiply(Axis.POSITIVE_X.rotationDegrees(backCapeRotation));
        ms.multiply(Axis.POSITIVE_Z.rotationDegrees(capeZOffset / 2.0F));
        ms.multiply(Axis.POSITIVE_Y.rotationDegrees(180.0F - capeZOffset / 1.25F));
        ms.translate(0, 0.05, -0.370); // Move back down
        if (player.isInSneakingPose()) {
            ms.translate(0, 0.15, 0.125);
        }


        StarPlatCloakModel.CAPE_MODEL.render(ms, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        ms.pop();
    }

    // [VanillaCopy] render the cape
    @Unique
    private void mythicmetals$renderHallowedCape(PoseStack ms, VertexConsumerProvider vertices, int light, AbstractClientPlayer player, float f, float g, float h, float j, float k, float l) {
        ms.push();
        ms.translate(0.0, 0.0, 0.125);
        double x = Mth.lerp(h, player.prevCapeX, player.capeX)
            - Mth.lerp(h, player.prevX, player.getX());
        double y = Mth.lerp(h, player.prevCapeY, player.capeY)
            - Mth.lerp(h, player.prevY, player.getY());
        double z = Mth.lerp(h, player.prevCapeZ, player.capeZ)
            - Mth.lerp(h, player.prevZ, player.getZ());
        float yaw = player.prevBodyYaw + (player.bodyYaw - player.prevBodyYaw);
        double o = Mth.sin(yaw * (float) (Math.PI / 180.0));
        double p = -Mth.cos(yaw * (float) (Math.PI / 180.0));
        float q = (float) y * 10.0F;
        q = Mth.clamp(q, -6.0F, 32.0F);
        float r = (float) (x * o + z * p) * 100.0F;
        r = Mth.clamp(r, 0.0F, 150.0F);
        float s = (float) (x * p - z * o) * 100.0F;
        s = Mth.clamp(s, -20.0F, 20.0F);
        if (r < 0.0F) {
            r = 0.0F;
        }

        float t = Mth.lerp(h, player.prevStrideDistance, player.strideDistance);
        q += Mth.sin(Mth.lerp(h, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;
        if (player.isInSneakingPose()) {
            q += 25.0F;
        }

        ms.multiply(Axis.POSITIVE_X.rotationDegrees(6.0F + r / 2.0F + q));
        ms.multiply(Axis.POSITIVE_Z.rotationDegrees(s / 2.0F));
        ms.multiply(Axis.POSITIVE_Y.rotationDegrees(180.0F - s / 2.0F));
        VertexConsumer vertexConsumer = vertices.getBuffer(RenderLayer.getEntityTranslucent(MythicModelHandler.HALLOWED_CAPE));
        this.getContextModel().renderCape(ms, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        ms.pop();
    }
}
