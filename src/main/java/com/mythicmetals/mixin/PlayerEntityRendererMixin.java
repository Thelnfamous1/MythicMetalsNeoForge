// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.mythicmetals.mixin;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.CarmotShield;
import com.mythicmetals.client.rendering.StormyxShieldRenderer;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.render.*;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.model.HumanoidModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mythicmetals.client.rendering.PlayerEnergySwirlFeatureRenderer.SWIRL_TEXTURE;
import static com.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors.SHIELD_BREAK_COLOR;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    /**
     * Renders the Carmot Shield on the players arm
     */
    @Inject(method = "renderHand", at = @At("TAIL"))
    private void mythicmetals$renderShieldArm(PoseStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayer player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        if (player.getData(MythicMetals.CARMOT_SHIELD).shouldRenderShield()) {
            final var client = Minecraft.getInstance();
            float f = player.age + (client.isPaused() ? 0 : client.getRenderTickCounter().getTickDelta(true));

            var shield = player.getComponent(MythicMetals.CARMOT_SHIELD);

            var consumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(SWIRL_TEXTURE, (f * .005f) % 1f, f * .005f % 1f));
            matrices.scale(1.0625f, 1.0625f, 1.0625f);
            if (shield.cooldown > CarmotShield.MAX_COOLDOWN - 30) {
                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, SHIELD_BREAK_COLOR);
            } else // Regular animation
                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, UsefulSingletonForColorUtil.rainbow());
        }
    }

    /**
     * Renders the Stormyx Shield around the player
     */
    @Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
        at = @At("TAIL"))
    private void mythicmetals$renderRainbowShield(AbstractClientPlayer player, float f, float g, PoseStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        var stack = player.getActiveItem();
        // Only render if the shield is actively being used
        if (stack.getItem().equals(MythicTools.STORMYX_SHIELD)) {
            matrixStack.push();
            StormyxShieldRenderer.renderRainbowShield(matrixStack, vertexConsumerProvider, i, player);
            matrixStack.pop();
        }
    }

    @Inject(method = "getArmPose", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$mythrilDrillPose(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        var stack = player.getStackInHand(hand);
        if (stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
        }
    }
}
