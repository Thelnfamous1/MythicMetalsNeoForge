package com.mythicmetals.armor;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

// TODO - Make this extend HallowedArmor and give it an epic model sometime
public class CelestiumArmor extends ArmorItem {

    public CelestiumArmor(ArmorMaterial material, Type type, Item.Properties settings) {
        super(RegistryHelper.getEntry(material), type, settings);
    }
}