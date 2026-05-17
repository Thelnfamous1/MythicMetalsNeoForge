package com.mythicmetals.armor;


import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public class RuniteArmorSet extends ArmorSet {

    public RuniteArmorSet(String name, Holder<ArmorMaterial> material, int duraMod) {
        super(name, material, duraMod);
    }

    @Override
    protected ArmorItem makeItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties settings) {
        if (slot != ArmorItem.Type.HELMET) return super.makeItem(material, slot, settings);
        return new RuniteArmor(slot, settings);
    }
}
