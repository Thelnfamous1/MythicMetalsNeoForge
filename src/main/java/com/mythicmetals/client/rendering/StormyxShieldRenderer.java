package com.mythicmetals.client.rendering;

import com.mythicmetals.client.models.RainbowShieldModel;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.render.*;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

public class StormyxShieldRenderer {
    private static final ResourceLocation WORLD_BORDER = ResourceLocation.of("textures/misc/forcefield.png");

    /**
     * Renders the model of the Stormyx Rainbow Shield, a fancy localized worldborder
     */
    public static void renderRainbowShield(PoseStack matrices, VertexConsumerProvider vcp, int light, AbstractClientPlayer player) {
        matrices.push();
        // Rainbow Handling
        double delta = System.currentTimeMillis() / 45.0;

        // Create and render rainbow shield
        var part = RainbowShieldModel.getTexturedModelData().createModel();
        part.scale(new Vector3f(player.getScale() - 1, player.getScale() - 1, player.getScale() - 1));
        part.render(
            matrices,
            vcp.getBuffer(RenderLayer.getEnergySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
            light,
            OverlayTexture.DEFAULT_UV,
            UsefulSingletonForColorUtil.rainbow());
        matrices.pop();
    }
}
