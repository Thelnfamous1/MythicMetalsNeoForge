package com.mythicmetals.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class CelestiumArmorSet extends ArmorSet {

    public CelestiumArmorSet(String name, Holder<ArmorMaterial> material, int duraMod, Consumer<Item.Properties> settingsConsumer) {
        super(name, material, duraMod, settingsConsumer);
    }

    @Override
    protected ArmorItem makeItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties settings) {
        return new CelestiumArmor(material, slot, settings);
    }
}
