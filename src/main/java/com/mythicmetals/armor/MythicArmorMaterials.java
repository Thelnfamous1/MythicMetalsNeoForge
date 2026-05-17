package com.mythicmetals.armor;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
//import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.core.Registry;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

@SuppressWarnings("CodeBlock2Expr")
public class MythicArmorMaterials /*implements AutoRegistryContainer<ArmorMaterial>*/ {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MythicMetals.MOD_ID);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ADAMANTITE = RegistryHelper.armorMaterial("adamantite", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, RegisterSounds.EQUIP_ADAMANTITE, () -> {
        return Ingredient.of(MythicItems.ADAMANTITE.getIngot());
    }, List.of(layer("adamantite")), 2.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> AQUARIUM = RegistryHelper.armorMaterial("aquarium", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 5,
        ArmorItem.Type.LEGGINGS, 4,
        ArmorItem.Type.BOOTS, 1), 12, RegisterSounds.EQUIP_AQUARIUM, () -> {
        return Ingredient.of(MythicItems.AQUARIUM.getIngot());
    }, List.of(layer("aquarium")), 0f, 0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BANGLUM = RegistryHelper.armorMaterial("banglum", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 6,
        ArmorItem.Type.LEGGINGS, 5,
        ArmorItem.Type.BOOTS, 2), 1, RegisterSounds.EQUIP_BANGLUM, () -> {
        return Ingredient.of(MythicItems.BANGLUM.getIngot());
    }, List.of(layer("banglum")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BRONZE = RegistryHelper.armorMaterial("bronze", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 6,
        ArmorItem.Type.LEGGINGS, 5,
        ArmorItem.Type.BOOTS, 2), 14, RegisterSounds.EQUIP_BRONZE, () -> {
        return Ingredient.of(MythicItems.BRONZE.getIngot());
    }, List.of(layer("bronze")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CARMOT = RegistryHelper.armorMaterial("carmot", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 40, RegisterSounds.EQUIP_CARMOT, () -> {
        return Ingredient.of(MythicItems.CARMOT.getIngot());
    }, List.of(layer("carmot")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CELESTIUM = RegistryHelper.armorMaterial("celestium", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 4,
        ArmorItem.Type.CHESTPLATE, 10,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 4), 30, RegisterSounds.EQUIP_CELESTIUM, () -> {
        return Ingredient.of(MythicItems.CELESTIUM.getIngot());
    }, List.of(layer("celestium")), 3.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> COPPER = RegistryHelper.armorMaterial("copper", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 4,
        ArmorItem.Type.LEGGINGS, 3,
        ArmorItem.Type.BOOTS, 1), 8, RegisterSounds.EQUIP_COPPER, () -> {
        return Ingredient.of(Items.COPPER_INGOT);
    }, List.of(layer("copper")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> DURASTEEL = RegistryHelper.armorMaterial("durasteel", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 12, RegisterSounds.EQUIP_DURASTEEL, () -> {
        return Ingredient.of(MythicItems.DURASTEEL.getIngot());
    }, List.of(layer("durasteel")), 1.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> HALLOWED = RegistryHelper.armorMaterial("hallowed", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 4,
        ArmorItem.Type.CHESTPLATE, 9,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 4), 20, RegisterSounds.EQUIP_HALLOWED, () -> {
        return Ingredient.of(MythicItems.HALLOWED.getIngot());
    }, List.of(layer("hallowed")), 4.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> KYBER = RegistryHelper.armorMaterial("kyber", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 20, RegisterSounds.EQUIP_KYBER, () -> {
        return Ingredient.of(MythicItems.KYBER.getIngot());
    }, List.of(layer("kyber")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LEGENDARY_BANGLUM = RegistryHelper.armorMaterial("legendary_banglum", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 2, RegisterSounds.EQUIP_LEGENDARY_BANGLUM, () -> {
        return Ingredient.of(MythicItems.BANGLUM.getIngot());
    }, List.of(layer("legendary_banglum")), 2.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> METALLURGIUM = RegistryHelper.armorMaterial("metallurgium", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 5,
        ArmorItem.Type.CHESTPLATE, 12,
        ArmorItem.Type.LEGGINGS, 8,
        ArmorItem.Type.BOOTS, 5), 30, RegisterSounds.EQUIP_METALLURGIUM, () -> {
        return Ingredient.of(MythicItems.METALLURGIUM.getIngot());
    }, List.of(layer("metallurgium")), 5.0f, 0.225f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> MIDAS_GOLD = RegistryHelper.armorMaterial("midas_gold", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 5,
        ArmorItem.Type.LEGGINGS, 3,
        ArmorItem.Type.BOOTS, 1), 24, RegisterSounds.EQUIP_MIDAS_GOLD, () -> {
        return Ingredient.of(MythicItems.MIDAS_GOLD.getIngot());
    }, List.of(layer("midas_gold")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> MYTHRIL = RegistryHelper.armorMaterial("mythril", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 22, RegisterSounds.EQUIP_MYTHRIL, () -> {
        return Ingredient.of(MythicItems.MYTHRIL.getIngot());
    }, List.of(layer("mythril")), 2.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ORICHALCUM = RegistryHelper.armorMaterial("orichalcum", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 4,
        ArmorItem.Type.CHESTPLATE, 9,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 4), 16, RegisterSounds.EQUIP_ORICHALCUM, () -> {
        return Ingredient.of(MythicItems.ORICHALCUM.getIngot());
    }, List.of(layer("orichalcum")), 3.0f, 0.1f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> OSMIUM = RegistryHelper.armorMaterial("osmium", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 7,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, RegisterSounds.EQUIP_OSMIUM, () -> {
        return Ingredient.of(MythicItems.OSMIUM.getIngot());
    }, List.of(layer("osmium")), 2.0f, 0.25f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> OSMIUM_CHAINMAIL = RegistryHelper.armorMaterial("osmium_chainmail", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 5,
        ArmorItem.Type.LEGGINGS, 4,
        ArmorItem.Type.BOOTS, 1), 15, RegisterSounds.EQUIP_OSMIUM_CHAINMAIL, () -> {
        return Ingredient.of(MythicItems.OSMIUM.getIngot());
    }, List.of(layer("osmium_chainmail")), 2.0f, 0.2f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> PALLADIUM = RegistryHelper.armorMaterial("palladium", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, RegisterSounds.EQUIP_PALLADIUM, () -> {
        return Ingredient.of(MythicItems.PALLADIUM.getIngot());
    }, List.of(layer("palladium")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> PROMETHEUM = RegistryHelper.armorMaterial("prometheum", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 17, RegisterSounds.EQUIP_PROMETHEUM, () -> {
        return Ingredient.of(MythicItems.PROMETHEUM.getIngot());
    }, List.of(layer("prometheum")), 1.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> RUNITE = RegistryHelper.armorMaterial("runite", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 13, RegisterSounds.EQUIP_RUNITE, () -> {
        return Ingredient.of(MythicItems.RUNITE.getIngot());
    }, List.of(layer("runite")), 2.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SILVER = RegistryHelper.armorMaterial("silver", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 4,
        ArmorItem.Type.LEGGINGS, 3,
        ArmorItem.Type.BOOTS, 1), 20, RegisterSounds.EQUIP_SILVER, () -> {
        return Ingredient.of(MythicItems.SILVER.getIngot());
    }, List.of(layer("silver")), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STAR_PLATINUM = RegistryHelper.armorMaterial("star_platinum", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 7,
        ArmorItem.Type.LEGGINGS, 7,
        ArmorItem.Type.BOOTS, 3), 18, RegisterSounds.EQUIP_STAR_PLATINUM, () -> {
        return Ingredient.of(MythicItems.STAR_PLATINUM.getIngot());
    }, List.of(layer("star_platinum")), 2.0f, 0.1f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STEEL = RegistryHelper.armorMaterial("steel", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 2,
        ArmorItem.Type.CHESTPLATE, 6,
        ArmorItem.Type.LEGGINGS, 5,
        ArmorItem.Type.BOOTS, 2), 10, RegisterSounds.EQUIP_STEEL, () -> {
        return Ingredient.of(MythicItems.STEEL.getIngot());
    }, List.of(layer("steel")), 0.5f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STORMYX = RegistryHelper.armorMaterial("stormyx", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 30, RegisterSounds.EQUIP_STORMYX, () -> {
        return Ingredient.of(MythicItems.STORMYX.getIngot());
    }, List.of(layer("stormyx")), 2.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> TIDESINGER = RegistryHelper.armorMaterial("tidesinger", () -> new ArmorMaterial(Map.of(
        ArmorItem.Type.HELMET, 3,
        ArmorItem.Type.CHESTPLATE, 8,
        ArmorItem.Type.LEGGINGS, 6,
        ArmorItem.Type.BOOTS, 3), 16, RegisterSounds.EQUIP_TIDESINGER, () -> {
        return Ingredient.of(MythicItems.AQUARIUM.getIngot());
    }, List.of(layer("tidesinger")), 2.0f, 0.0f));

    /*
    @Override
    public Registry<ArmorMaterial> getRegistry() {
        return BuiltInRegistries.ARMOR_MATERIAL;
    }

    @Override
    public Class<ArmorMaterial> getTargetFieldType() {
        return ArmorMaterial.class;
    }
     */

    private static Holder<SoundEvent> sound(SoundEvent sound) {
        return BuiltInRegistries.SOUND_EVENT.wrapAsHolder(sound);
    }

    private static ArmorMaterial.Layer layer(String name) {
        return new ArmorMaterial.Layer(RegistryHelper.id(name));
    }

    public static void init() {

    }
}
