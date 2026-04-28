package com.mythicmetals.armor;

import com.mythicmetals.client.models.HelmetModel;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class HallowedArmor extends ArmorItem {

    @Environment(EnvType.CLIENT)
    private HumanoidModel<LivingEntity> model;
    public final ArmorItem.Type type;

    public HallowedArmor(ArmorItem.Type type, Settings settings) {
        this(MythicArmorMaterials.HALLOWED, type, settings);
    }

    public HallowedArmor(ArmorMaterial material, ArmorItem.Type slot, Settings settings) {
        super(RegistryHelper.getEntry(material), slot, settings);
        this.type = slot;
    }

    @Environment(EnvType.CLIENT)
    public HumanoidModel<LivingEntity> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(type.getEquipmentSlot());
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    protected HumanoidModel<LivingEntity> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = Minecraft.getInstance().getEntityModelLoader();
        var root = models.getModelPart(MythicModelHandler.HALLOWED_ARMOR);
        return new HelmetModel(root, slot);
    }

    @NotNull
    public ResourceLocation getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/hallowed_model.png");
    }
}
