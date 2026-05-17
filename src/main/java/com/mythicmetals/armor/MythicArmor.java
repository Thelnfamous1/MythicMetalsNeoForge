package com.mythicmetals.armor;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.misc.RegistryHelper;
//import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;

//import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MythicArmor /*implements SimpleFieldProcessingSubject<ArmorSet>*/ {
    public static final BiMap<String, ArmorSet> ARMOR_MAP = HashBiMap.create();
    public static final ArmorSet ADAMANTITE = new ArmorSet("adamantite", MythicArmorMaterials.ADAMANTITE, 30);
    public static final ArmorSet AQUARIUM = new ArmorSet("aquarium", MythicArmorMaterials.AQUARIUM, 20);
    public static final ArmorSet BANGLUM = new ArmorSet("banglum", MythicArmorMaterials.BANGLUM, 14);
    public static final ArmorSet BRONZE = new ArmorSet("bronze", MythicArmorMaterials.BRONZE, 15);
    public static final ArmorSet CARMOT = new ArmorSet("carmot", MythicArmorMaterials.CARMOT, 26, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet CELESTIUM = new CelestiumArmorSet("celestium", MythicArmorMaterials.CELESTIUM, 41, settings -> settings.rarity(Rarity.RARE).fireResistant());
    public static final DeferredItem<Item> CELESTIUM_ELYTRA = RegistryHelper.item("celestium_elytra", () -> new CelestiumElytra(new Item.Properties()
        .rarity(Rarity.RARE)
        .group(MythicMetals.TABBED_GROUP).tab(3)
        .durability(832)
        .attributes(CelestiumElytra.createDefaultAttributes())
    ));
    public static final ArmorSet COPPER = new ArmorSet("copper", MythicArmorMaterials.COPPER, 9);
    public static final ArmorSet DURASTEEL = new ArmorSet("durasteel", MythicArmorMaterials.DURASTEEL, 25);
    public static final ArmorSet HALLOWED = new HallowedArmorSet("hallowed", MythicArmorMaterials.HALLOWED, 41, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet KYBER = new ArmorSet("kyber", MythicArmorMaterials.KYBER, 21);
    public static final ArmorSet LEGENDARY_BANGLUM = new BanglumArmorSet("legendary_banglum", MythicArmorMaterials.LEGENDARY_BANGLUM, 28, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet METALLURGIUM = new MetallurgiumArmorSet("metallurgium", MythicArmorMaterials.METALLURGIUM, 69, settings -> settings.fireResistant().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet("midas_gold", MythicArmorMaterials.MIDAS_GOLD, 14);
    public static final ArmorSet MYTHRIL = new ArmorSet("mythril", MythicArmorMaterials.MYTHRIL, 31);
    public static final ArmorSet ORICHALCUM = new ArmorSet("orichalcum", MythicArmorMaterials.ORICHALCUM, 40);
    public static final ArmorSet OSMIUM = new ArmorSet("osmium", MythicArmorMaterials.OSMIUM, 25);
    public static final ArmorSet OSMIUM_CHAINMAIL = new ArmorSet("osmium_chainmail", MythicArmorMaterials.OSMIUM_CHAINMAIL, 25);
    public static final ArmorSet PALLADIUM = new ArmorSet("palladium", MythicArmorMaterials.PALLADIUM, 28, Item.Properties::fireResistant);
    public static final ArmorSet PROMETHEUM = new ArmorSet("prometheum", MythicArmorMaterials.PROMETHEUM, 18, settings -> settings.component(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT));
    public static final ArmorSet RUNITE = new RuniteArmorSet("runite", MythicArmorMaterials.RUNITE, 27);
    public static final ArmorSet SILVER = new ArmorSet("silver", MythicArmorMaterials.SILVER, 10);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet("star_platinum", MythicArmorMaterials.STAR_PLATINUM, 34);
    public static final ArmorSet STEEL = new ArmorSet("steel", MythicArmorMaterials.STEEL, 20);
    public static final ArmorSet STORMYX = new ArmorSet("stormyx", MythicArmorMaterials.STORMYX, 30);
    public static final ArmorSet TIDESINGER = new TidesingerArmorSet("tidesinger", MythicArmorMaterials.TIDESINGER, 32, settings -> settings.rarity(Rarity.UNCOMMON));

    public static void init() {

    }

    /*
    @Override
    public void processField(ArmorSet armorSet, String name, Field f) {
        armorSet.register(name);
        ARMOR_MAP.put(name, armorSet);
    }

    @Override
    public void afterFieldProcessing() {
        RegistryHelper.item("celestium_elytra", CELESTIUM_ELYTRA);
    }

    @Override
    public Class<ArmorSet> getTargetFieldType() {
        return ArmorSet.class;
    }
     */
}
