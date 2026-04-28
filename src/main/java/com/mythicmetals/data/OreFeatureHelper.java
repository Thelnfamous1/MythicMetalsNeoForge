package com.mythicmetals.data;

import com.google.common.collect.ImmutableList;
import com.mythicmetals.config.OreConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.block.Block;
import net.minecraft.registry.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import java.util.List;

/**
 * A helper class for adding creating and adding ore features to the world.
 */
public class OreFeatureHelper {

    public static void ore(RegistryKey<PlacedFeature> ore, TagKey<Biome> tag) {
        BiomeModifications.addFeature(BiomeSelectors.tag(tag), GenerationStep.Decoration.UNDERGROUND_ORES, ore);
    }

    public static void configuredFeature(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> featureKey, RuleTest rule, Block oreBlock, OreConfig config) {
        ConfiguredFeatures.register(registerable, featureKey, Feature.ORE, configuredConfig(rule, oreBlock, config));
    }

    public static void configuredFeature(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> featureKey, ImmutableList<OreFeatureConfig.Target> target, OreConfig config) {
        ConfiguredFeatures.register(registerable, featureKey, Feature.ORE, configuredConfig(target, config));
    }

    public static OreFeatureConfig configuredConfig(RuleTest test, Block block, OreConfig config) {
        return new OreFeatureConfig(test, block.getDefaultState(), config.veinSize, config.discardChance);
    }

    public static OreFeatureConfig configuredConfig(ImmutableList<OreFeatureConfig.Target> target, OreConfig config) {
        return new OreFeatureConfig(target, config.veinSize, config.discardChance);
    }

    public static void create(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var b = config.offset && config.trapezoid; // Check if both offset and trapezoid is being used at the same time.
        if (b) {
            throw new IllegalArgumentException(registerable.toString() + " cannot be offset and trapezoid at the same time.");
        } else if (config.offset) {
            placeAboveBottom(registerable, configuredFeatureKey, placedFeatureKey, config);
        } else if (config.trapezoid) {
            placeTrapezoid(registerable, configuredFeatureKey, placedFeatureKey, config);
        } else {
            placeUniform(registerable, configuredFeatureKey, placedFeatureKey, config);
        }
    }

    public static void placeUniform(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        PlacedFeatures.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(VerticalAnchor.fixed(config.bottom), VerticalAnchor.fixed(config.top))));
    }

    public static void placeAboveBottom(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        PlacedFeatures.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacementModifier.uniform(VerticalAnchor.aboveBottom(config.bottom), VerticalAnchor.fixed(config.top))));
    }

    public static void placeTrapezoid(Registerable<PlacedFeature> registerable, RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey, RegistryKey<PlacedFeature> placedFeatureKey, OreConfig config) {
        var featureLookup = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        PlacedFeatures.register(registerable, placedFeatureKey, featureLookup.getOrThrow(configuredFeatureKey), modifiersWithCount(config.perChunk, HeightRangePlacementModifier.trapezoid(VerticalAnchor.fixed(config.bottom), VerticalAnchor.fixed(config.top))));
    }

    //From Mojanks OrePlacedFeatures
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

}
