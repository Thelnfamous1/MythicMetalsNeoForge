package com.mythicmetals.client.models;

import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.mixin.ModelLayersAccessor;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.resources.ResourceLocation;
import java.util.function.BiConsumer;

public class MythicModelHandler {
    public static final EntityModelLayer BANGLUM = model("banglum_armor");
    public static final EntityModelLayer CARMOT_SWIRL = model("carmot_swirl");
    public static final EntityModelLayer HALLOWED_ARMOR = model("hallowed_armor");
    public static final EntityModelLayer METALLURGIUM = model("metallurgium_armor");
    public static final EntityModelLayer RUNITE = model("runite_armor");
    public static final EntityModelLayer TIDESINGER = model("tidesinger");
    public static final EntityModelLayer BANGLUM_TNT_MINECART = model("banglum_tnt_minecart");
    public static final EntityModelLayer PALLADIUM_MINECART = model("palladium_minecart");
    public static final ResourceLocation HALLOWED_CAPE = RegistryHelper.id("textures/models/hallowed_cape.png");
    public static final ResourceLocation STAR_PLATINUM_CLOAK = RegistryHelper.id("textures/models/star_platinum_cloak.png");

    public static void init(BiConsumer<EntityModelLayer, LayerDefinition> consumer) {
        consumer.accept(BANGLUM_TNT_MINECART, MinecartEntityModel.getTexturedModelData());
        consumer.accept(PALLADIUM_MINECART, MinecartEntityModel.getTexturedModelData());
        consumer.accept(BANGLUM, LayerDefinition.of(BanglumArmorModel.getModelData(), 64, 32));
        consumer.accept(CARMOT_SWIRL, LayerDefinition.of(PlayerEntityModel.getTexturedModelData(new Dilation(1.15f), false), 64, 32));
        consumer.accept(HALLOWED_ARMOR, LayerDefinition.of(HallowedArmorModel.getModelData(), 64, 32));
        consumer.accept(METALLURGIUM, LayerDefinition.of(MetallurgiumArmorModel.getModelData(), 32, 32));
        consumer.accept(RUNITE, LayerDefinition.of(RuniteArmorModel.getModelData(), 64, 32));
        consumer.accept(TIDESINGER, LayerDefinition.of(TidesingerArmorModel.getModelData(), 128, 128));
    }

    /**
     * Add a custom model layer into the global map for loading<br>
     * Shoutouts to williewillus for this implementation:
     * <a href="https://github.com/VazkiiMods/Botania/blob/1.18.x-fabric/src/main/java/vazkii/botania/client/model/ModModelLayers.java">Source</a>
     *
     * @see net.minecraft.client.render.entity.model.EntityModelLayers#LAYERS
     */
    public static EntityModelLayer model(String name, String layer) {
        var result = new EntityModelLayer(RegistryHelper.id(name), layer);
        ModelLayersAccessor.getLAYERS().add(result);
        return result;
    }

    public static EntityModelLayer model(String name) {
        return model(name, "main");
    }
}
