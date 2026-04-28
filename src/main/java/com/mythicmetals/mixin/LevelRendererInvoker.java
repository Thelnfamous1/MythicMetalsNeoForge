package com.mythicmetals.mixin;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// [ConjuringCopy] https://github.com/wisp-forest/conjuring/blob/1.18/src/main/java/com/glisco/conjuring/mixin/WorldRendererInvoker.java
@Mixin(LevelRenderer.class)
public interface LevelRendererInvoker {

    @Invoker("renderShape")
    static void mythicmetals$drawShapeOutline(
            PoseStack matrixStack,
            VertexConsumer vertexConsumer,
            VoxelShape voxelShape,
            double offsetX,
            double offsetY,
            double offsetZ,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        throw new AssertionError();
    }

}
