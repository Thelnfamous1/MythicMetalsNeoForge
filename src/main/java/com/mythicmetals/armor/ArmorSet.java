package com.mythicmetals.armor;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ArmorSet {

    private final DeferredItem<ArmorItem> helmet;
    private final DeferredItem<ArmorItem> chestplate;
    private final DeferredItem<ArmorItem> leggings;
    private final DeferredItem<ArmorItem> boots;

    private final List<DeferredItem<ArmorItem>> items;
    private final String name;
    private final Holder<ArmorMaterial> material;

    private static final Map<ArmorItem.Type, Integer> BASE_DURABILITY = Map.of(
        ArmorItem.Type.HELMET, 12,
        ArmorItem.Type.CHESTPLATE, 16,
        ArmorItem.Type.LEGGINGS, 15,
        ArmorItem.Type.BOOTS, 13
    );

    public ArmorItem baseArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, int durabilityModifier, Consumer<Item.Properties> settingsProcessor) {
        final var settings = new Item.Properties()
            .group(MythicMetals.TABBED_GROUP)
            .tab(3)
            .durability(BASE_DURABILITY.get(slot) * durabilityModifier);
        settingsProcessor.accept(settings);
        return this.makeItem(material, slot, settings);
    }

    public ArmorSet(String name,
                    Holder<ArmorMaterial> material, int durabilityModifier) {
        this(name, material, durabilityModifier, settings -> {
        });
    }

    public ArmorSet(String name,
                    Holder<ArmorMaterial> material, int durabilityModifier, Consumer<Item.Properties> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.helmet = MythicItems.ITEMS.register(
                name + "_helmet", () -> makeItem(material, ArmorItem.Type.HELMET, createProperties(ArmorItem.Type.HELMET, durabilityModifier, settingsProcessor)));
        this.chestplate = MythicItems.ITEMS.register(
                name + "_chestplate", () -> makeItem(material, ArmorItem.Type.CHESTPLATE, createProperties(ArmorItem.Type.CHESTPLATE, durabilityModifier, settingsProcessor)));
        this.leggings = MythicItems.ITEMS.register(
                name + "_leggings", () -> makeItem(material, ArmorItem.Type.LEGGINGS, createProperties(ArmorItem.Type.LEGGINGS, durabilityModifier, settingsProcessor)));
        this.boots = MythicItems.ITEMS.register(
                name + "_boots", () -> makeItem(material, ArmorItem.Type.BOOTS, createProperties(ArmorItem.Type.BOOTS, durabilityModifier, settingsProcessor)));
        this.items = List.of(helmet, chestplate, leggings, boots);
    }

    public static ArmorSet register(
            String name,
            Holder<ArmorMaterial> material,
            int durabilityModifier
    ) {
        return new ArmorSet(
                name,
                material,
                durabilityModifier,
                props -> {}
        );
    }

    public static ArmorSet register(
            String name,
            Holder<ArmorMaterial> material,
            int durabilityModifier,
            Consumer<Item.Properties> settingsProcessor
    ) {
        return new ArmorSet(
                name,
                material,
                durabilityModifier,
                settingsProcessor
        );
    }

    public void register(String name) {
        /*
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_helmet"), helmet);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_chestplate"), chestplate);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_leggings"), leggings);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_boots"), boots);
         */
    }

    public void register(String modid, String name) {
        /*
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_helmet"), helmet);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_chestplate"), chestplate);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_leggings"), leggings);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modid, name + "_boots"), boots);
         */
    }

    protected Item.Properties createProperties(
            ArmorItem.Type slot,
            int durabilityModifier,
            Consumer<Item.Properties> settingsProcessor
    ) {
        Item.Properties props = new Item.Properties()
                .group(MythicMetals.TABBED_GROUP)
                .tab(3)
                .durability(BASE_DURABILITY.get(slot) * durabilityModifier);

        settingsProcessor.accept(props);

        return props;
    }

    protected ArmorItem makeItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties settings) {
        return new ArmorItem(material, slot, settings);
    }

    public ArmorItem getHelmet() {
        return helmet.get();
    }

    public ArmorItem getChestplate() {
        return chestplate.get();
    }

    public ArmorItem getLeggings() {
        return leggings.get();
    }

    public ArmorItem getBoots() {
        return boots.get();
    }

    public static Map<ArmorItem.Type, Integer> getBaseDurability() {
        return BASE_DURABILITY;
    }

    public List<ArmorItem> getArmorItems() {
        return items.stream().map(DeferredHolder::get).toList();
    }

    public boolean isInArmorSet(ItemStack stack) {
        return items.stream()
                .map(DeferredItem::get)
                .anyMatch(item -> item == stack.getItem());
    }

    public Holder<ArmorMaterial> materialHolder(ArmorMaterial material) {
        return BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(material);
    }

    public String getTitlecaseName() {
        return StringUtilsAtHome.toTitleCase(MythicArmor.ARMOR_MAP.inverse().get(this));
    }

    public String getMaterialId() {
        return MythicArmor.ARMOR_MAP.inverse().get(this);
    }
}
