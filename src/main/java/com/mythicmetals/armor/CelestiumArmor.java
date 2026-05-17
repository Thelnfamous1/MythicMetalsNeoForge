package com.mythicmetals.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

// TODO - Make this extend HallowedArmor and give it an epic model sometime
public class CelestiumArmor extends ArmorItem {

    public CelestiumArmor(Holder<ArmorMaterial> material, Type type, Item.Properties settings) {
        super(material, type, settings);
    }
}