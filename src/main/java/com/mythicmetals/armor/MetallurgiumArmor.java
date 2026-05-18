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

public class MetallurgiumArmor extends HallowedArmor {

    public MetallurgiumArmor(ArmorItem.Type type, Item.Properties settings) {
        this(MythicArmorMaterials.METALLURGIUM, type, settings);
    }

    public MetallurgiumArmor(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties settings) {
        super(material, type, settings);
    }

    //@Environment(EnvType.CLIENT)
    protected Object provideArmorModelForSlot(EquipmentSlot slot) {
        var models = Minecraft.getInstance().getEntityModels();
        var root = models.bakeLayer(MythicModelHandler.METALLURGIUM);
        return new HelmetModel(root, slot);
    }

    @NotNull
    @Override
    public final ResourceLocation getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/metallurgium_model.png");
    }
}
