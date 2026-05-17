package com.mythicmetals.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class BanglumArmorSet extends ArmorSet {

    public BanglumArmorSet(String name, Holder<ArmorMaterial> material, int duraMod, Consumer<Item.Properties> settingsProcessor) {
        super(name, material, duraMod, settingsProcessor);
    }

    @Override
    protected ArmorItem makeItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties settings) {
        if (slot != ArmorItem.Type.HELMET) return super.makeItem(material, slot, settings);
        return new BanglumArmor(slot, settings);
    }
}
