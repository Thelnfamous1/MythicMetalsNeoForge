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

public class BanglumArmor extends HallowedArmor {

    public BanglumArmor(ArmorItem.Type type, Item.Properties settings) {
        this(MythicArmorMaterials.LEGENDARY_BANGLUM, type, settings);
    }

    public BanglumArmor(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties settings) {
        super(material, type, settings);
    }

    //@Environment(EnvType.CLIENT)
    protected Object provideArmorModelForSlot(EquipmentSlot slot) {
        var models = Minecraft.getInstance().getEntityModels();
        var root = models.bakeLayer(MythicModelHandler.BANGLUM);
        return new HelmetModel(root, slot);
    }

    @NotNull
    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/banglum_model.png");
    }
}
