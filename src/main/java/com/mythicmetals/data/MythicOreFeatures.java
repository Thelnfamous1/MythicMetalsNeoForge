package com.mythicmetals.data;

import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.mythicmetals.MythicMetals.CONFIG;

public class MythicOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AQUARIUM = RegistryHelper.configuredFeatureKey("ore_aquarium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BANGLUM = RegistryHelper.configuredFeatureKey("ore_banglum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KYBER = RegistryHelper.configuredFeatureKey("ore_kyber");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MANGANESE = RegistryHelper.configuredFeatureKey("ore_manganese");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OSMIUM = RegistryHelper.configuredFeatureKey("ore_osmium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PLATINUM = RegistryHelper.configuredFeatureKey("ore_platinum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_QUADRILLUM = RegistryHelper.configuredFeatureKey("ore_quadrillum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUNITE = RegistryHelper.configuredFeatureKey("ore_runite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER = RegistryHelper.configuredFeatureKey("ore_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TIN = RegistryHelper.configuredFeatureKey("ore_tin");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ADAMANTITE = RegistryHelper.configuredFeatureKey("ore_adamantite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CALCITE_KYBER = RegistryHelper.configuredFeatureKey("ore_calcite_kyber");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CARMOT = RegistryHelper.configuredFeatureKey("ore_carmot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEEPSLATE_RUNITE = RegistryHelper.configuredFeatureKey("ore_deepslate_runite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MORKITE = RegistryHelper.configuredFeatureKey("ore_morkite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MYTHRIL = RegistryHelper.configuredFeatureKey("ore_mythril");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ORICHALCUM = RegistryHelper.configuredFeatureKey("ore_orichalcum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PROMETHEUM = RegistryHelper.configuredFeatureKey("ore_prometheum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_STARRITE = RegistryHelper.configuredFeatureKey("ore_starrite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_UNOBTAINIUM = RegistryHelper.configuredFeatureKey("ore_unobtainium");

    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_BANGLUM = RegistryHelper.configuredFeatureKey("ore_nether_banglum");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_MIDAS_GOLD = RegistryHelper.configuredFeatureKey("ore_midas_gold");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_PALLADIUM = RegistryHelper.configuredFeatureKey("ore_palladium");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_STORMYX = RegistryHelper.configuredFeatureKey("ore_stormyx");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORE_END_STARRITE = RegistryHelper.configuredFeatureKey("ore_end_starrite");

    // RegistryKeys for features
    public static final ResourceKey<PlacedFeature> ADAMANTITE = ResourceKey.of(Registries.PLACED_FEATURE, ORE_ADAMANTITE.getValue());
    public static final ResourceKey<PlacedFeature> AQUARIUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_AQUARIUM.getValue());
    public static final ResourceKey<PlacedFeature> BANGLUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_BANGLUM.getValue());
    public static final ResourceKey<PlacedFeature> NETHER_BANGLUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_NETHER_BANGLUM.getValue());
    public static final ResourceKey<PlacedFeature> CARMOT = ResourceKey.of(Registries.PLACED_FEATURE, ORE_CARMOT.getValue());
    public static final ResourceKey<PlacedFeature> CALCITE_KYBER = ResourceKey.of(Registries.PLACED_FEATURE, ORE_CALCITE_KYBER.getValue());
    public static final ResourceKey<PlacedFeature> END_STARRITE = ResourceKey.of(Registries.PLACED_FEATURE, ORE_END_STARRITE.getValue());
    public static final ResourceKey<PlacedFeature> KYBER = ResourceKey.of(Registries.PLACED_FEATURE, ORE_KYBER.getValue());
    public static final ResourceKey<PlacedFeature> MANGANESE = ResourceKey.of(Registries.PLACED_FEATURE, ORE_MANGANESE.getValue());
    public static final ResourceKey<PlacedFeature> MIDAS_GOLD = ResourceKey.of(Registries.PLACED_FEATURE, ORE_MIDAS_GOLD.getValue());
    public static final ResourceKey<PlacedFeature> MORKITE = ResourceKey.of(Registries.PLACED_FEATURE, ORE_MORKITE.getValue());
    public static final ResourceKey<PlacedFeature> MYTHRIL = ResourceKey.of(Registries.PLACED_FEATURE, ORE_MYTHRIL.getValue());
    public static final ResourceKey<PlacedFeature> ORICHALCUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_ORICHALCUM.getValue());
    public static final ResourceKey<PlacedFeature> OSMIUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_OSMIUM.getValue());
    public static final ResourceKey<PlacedFeature> PALLADIUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_PALLADIUM.getValue());
    public static final ResourceKey<PlacedFeature> PLATINUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_PLATINUM.getValue());
    public static final ResourceKey<PlacedFeature> PROMETHEUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_PROMETHEUM.getValue());
    public static final ResourceKey<PlacedFeature> QUADRILLUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_QUADRILLUM.getValue());
    public static final ResourceKey<PlacedFeature> DEEPSLATE_RUNITE = ResourceKey.of(Registries.PLACED_FEATURE, ORE_DEEPSLATE_RUNITE.getValue());
    public static final ResourceKey<PlacedFeature> RUNITE = ResourceKey.of(Registries.PLACED_FEATURE, ORE_RUNITE.getValue());
    public static final ResourceKey<PlacedFeature> SILVER = ResourceKey.of(Registries.PLACED_FEATURE, ORE_SILVER.getValue());
    public static final ResourceKey<PlacedFeature> STARRITE = ResourceKey.of(Registries.PLACED_FEATURE, ORE_STARRITE.getValue());
    public static final ResourceKey<PlacedFeature> STORMYX = ResourceKey.of(Registries.PLACED_FEATURE, ORE_STORMYX.getValue());
    public static final ResourceKey<PlacedFeature> TIN = ResourceKey.of(Registries.PLACED_FEATURE, ORE_TIN.getValue());
    public static final ResourceKey<PlacedFeature> UNOBTAINIUM = ResourceKey.of(Registries.PLACED_FEATURE, ORE_UNOBTAINIUM.getValue());

    public static void init() {

        //Overworld Ores
        if (CONFIG.adamantite()) {
            OreFeatureHelper.ore(ADAMANTITE, MythicOreBiomeTags.ADAMANTITE_BIOMES);
        }
        if (CONFIG.banglum()) {
            OreFeatureHelper.ore(BANGLUM, MythicOreBiomeTags.BANGLUM_BIOMES);
        }
        if (CONFIG.carmot()) {
            OreFeatureHelper.ore(CARMOT, MythicOreBiomeTags.CARMOT_BIOMES);
        }
        if (CONFIG.kyber()) {
            OreFeatureHelper.ore(KYBER, MythicOreBiomeTags.KYBER_BIOMES);
            OreFeatureHelper.ore(CALCITE_KYBER, MythicOreBiomeTags.CALCITE_KYBER_BIOMES);
        }
        if (CONFIG.mythril()) {
            OreFeatureHelper.ore(MYTHRIL, MythicOreBiomeTags.MYTHRIL_BIOMES);
        }
        if (CONFIG.orichalcum()) {
            OreFeatureHelper.ore(ORICHALCUM, MythicOreBiomeTags.ORICHALCUM_BIOMES);
        }
        if (CONFIG.manganese()) {
            OreFeatureHelper.ore(MANGANESE, MythicOreBiomeTags.MANGANESE_BIOMES);
        }
        if (CONFIG.platinum()) {
            OreFeatureHelper.ore(PLATINUM, MythicOreBiomeTags.PLATINUM_BIOMES);
        }
        if (CONFIG.quadrillum()) {
            OreFeatureHelper.ore(QUADRILLUM, MythicOreBiomeTags.QUADRILLUM_BIOMES);
        }
        if (CONFIG.runite()) {
            OreFeatureHelper.ore(RUNITE, MythicOreBiomeTags.RUNITE_BIOMES);
            OreFeatureHelper.ore(DEEPSLATE_RUNITE, MythicOreBiomeTags.DEEPSLATE_RUNITE_BIOMES);
        }
        if (CONFIG.silver()) {
            OreFeatureHelper.ore(SILVER, MythicOreBiomeTags.SILVER_BIOMES);
        }
        if (CONFIG.starrite()) {
            OreFeatureHelper.ore(STARRITE, MythicOreBiomeTags.STARRITE_BIOMES);
        }
        if (CONFIG.endStarrite()) {
            OreFeatureHelper.ore(END_STARRITE, MythicOreBiomeTags.END_STARRITE_BIOMES);
        }
        if (CONFIG.tin()) {
            OreFeatureHelper.ore(TIN, MythicOreBiomeTags.TIN_BIOMES);
        }
        if (CONFIG.unobtainium()) {
            OreFeatureHelper.ore(UNOBTAINIUM, MythicOreBiomeTags.UNOBTAINIUM_BIOMES);
        }
        if (CONFIG.morkite()) {
            OreFeatureHelper.ore(MORKITE, MythicOreBiomeTags.MORKITE_BIOMES);
        }

        //Nether Ores
        if (CONFIG.banglum()) {
            OreFeatureHelper.ore(NETHER_BANGLUM, MythicOreBiomeTags.NETHER_BANGLUM_BIOMES);
        }
        if (CONFIG.midasGold()) {
            OreFeatureHelper.ore(MIDAS_GOLD, MythicOreBiomeTags.MIDAS_GOLD_BIOMES);
        }
        if (CONFIG.stormyx()) {
            OreFeatureHelper.ore(STORMYX, MythicOreBiomeTags.STORMYX_BIOMES);
        }
        if (CONFIG.palladium()) {
            OreFeatureHelper.ore(PALLADIUM, MythicOreBiomeTags.PALLADIUM_BIOMES);
        }

        // Add Aquarium to Aquatic Biomes
        if (CONFIG.aquarium()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicOreBiomeTags.AQUARIUM_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, AQUARIUM);
        }
        // Add Prometheum to hot biomes
        if (CONFIG.prometheum()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicOreBiomeTags.PROMETHEUM_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, PROMETHEUM);
        }
        // Add Osmium to mountainous biomes
        if (CONFIG.osmium()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(MythicOreBiomeTags.OSMIUM_BIOMES), GenerationStep.Decoration.UNDERGROUND_ORES, OSMIUM);
        }
    }

}
