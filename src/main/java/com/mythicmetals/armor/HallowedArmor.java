package com.mythicmetals.armor;

import com.mythicmetals.client.models.HelmetModel;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HallowedArmor extends ArmorItem {

    //@Environment(EnvType.CLIENT)
    private Object model;

    public HallowedArmor(ArmorItem.Type type, Item.Properties settings) {
        this(MythicArmorMaterials.HALLOWED, type, settings);
    }

    public HallowedArmor(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties settings) {
        super(material, slot, settings);
    }

    //@Environment(EnvType.CLIENT)
    public Object getArmorModel() {
        if (this.model == null) {
            this.model = provideArmorModelForSlot(this.type.getSlot());
        }
        return this.model;
    }

    //@Environment(EnvType.CLIENT)
    protected Object provideArmorModelForSlot(EquipmentSlot slot) {
        var models = Minecraft.getInstance().getEntityModels();
        var root = models.bakeLayer(MythicModelHandler.HALLOWED_ARMOR);
        return new HelmetModel(root, slot);
    }

    @NotNull
    public ResourceLocation getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/hallowed_model.png");
    }
}
