package com.mythicmetals.block;

import com.google.common.collect.*;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
//import io.wispforest.owo.util.Maldenhagen;
import io.wispforest.owo.util.TagInjector;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class is a container which is used for the creation for all the blocks in Mythic Metals.
 * For creating blocks using this you want to start with looking at the {@link Builder}, which
 * contains all the methods for creating blocks.
 *
 * @author glisco
 * @author Noaaan
 */
@SuppressWarnings({"unused"})
public class BlockSet {
    private final DeferredBlock<? extends DropExperienceBlock> ore;
    private final DeferredBlock<? extends Block> storageBlock;
    private final DeferredBlock<? extends Block> oreStorageBlock;
    private final DeferredBlock<? extends AnvilBlock> anvil;

    private final String name;
    private final boolean fireproof;

    private final Multimap<DeferredBlock<?>, ResourceLocation> miningLevels;
    private final Multimap<DeferredBlock<?>, ResourceLocation> anvilMap;
    private final Map<String, DeferredBlock<? extends DropExperienceBlock>> oreVariants;
    private final boolean uncommon;

    /**
     * This constructor collects the smaller constructors from the {@link Builder} and creates a set of blocks.
     * Use {@link Builder#begin(String, boolean) BlockSet.Builder.begin} to begin,
     * and call {@link Builder#finish()} when you are done.
     *
     * @param name            Common name for the entire set of blocks, applies to every block created.
     * @param ore             Contains a vanilla {@link DropExperienceBlock}.
     * @param storageBlock    Contains a {@link Block} which is used as a storage block.
     * @param oreStorageBlock Contains a {@link Block} which is used as a ore storage block.
     * @param anvil           Contains an {@link AnvilBlock}
     * @param oreVariants     A map of a string and {@link DropExperienceBlock} which is used for variant ores.
     * @param fireproof       Boolean for creating fireproof block sets.
     * @param miningLevels    A map containing all the blocks being registered with their corresponding mining levels.
     * @param anvilMap        A map containing all anvils and their levels, so that they can be disabled.
     * @param uncommon        Boolean for setting the block item to Uncommon Rarity, changing the color of the text
     */
    private BlockSet(String name,
                     DeferredBlock<? extends DropExperienceBlock> ore,
                     DeferredBlock<? extends Block> storageBlock,
                     DeferredBlock<? extends Block> oreStorageBlock,
                     DeferredBlock<? extends AnvilBlock> anvil,
                     Map<String, DeferredBlock<? extends DropExperienceBlock>> oreVariants,
                     boolean fireproof,
                     Multimap<DeferredBlock<?>, ResourceLocation> miningLevels,
                     Multimap<DeferredBlock<?>, ResourceLocation> anvilMap, boolean uncommon) {

        this.name = name;
        this.fireproof = fireproof;

        this.ore = ore;
        this.storageBlock = storageBlock;
        this.oreStorageBlock = oreStorageBlock;
        this.anvil = anvil;

        this.oreVariants = oreVariants;
        this.miningLevels = miningLevels;
        this.anvilMap = anvilMap;
        this.uncommon = uncommon;
    }

    private void register() {

        /*
        if (ore != null) {
            RegistryHelper.block(name + "_ore", ore, fireproof, uncommon);
        }

        oreVariants.forEach((s, block) -> RegistryHelper.block(s + "_" + name + "_ore", block, fireproof, uncommon));

        if (oreStorageBlock != null) {
            RegistryHelper.block("raw_" + name + "_block", oreStorageBlock, fireproof, uncommon);
        }
        if (storageBlock != null) {
            RegistryHelper.block(name + "_block", storageBlock, fireproof, uncommon);
        }
        if (anvil != null) {
            RegistryHelper.block(name + "_anvil", anvil, fireproof, uncommon);
        }
         */
        // Inject all the mining levels into their tags.
        if (MythicMetals.CONFIG.enableAnvils()) {
            anvilMap.forEach(((anvilBlock, level) -> {
                TagInjector.inject(BuiltInRegistries.BLOCK, RegistryHelper.id("anvils"), anvilBlock.get());
                TagInjector.inject(BuiltInRegistries.BLOCK, level, anvilBlock.get());
                TagInjector.inject(BuiltInRegistries.BLOCK, ResourceLocation.withDefaultNamespace("anvil"), anvilBlock.get());
                TagInjector.inject(BuiltInRegistries.ITEM, ResourceLocation.withDefaultNamespace("anvil"), anvilBlock.asItem());
            }));
        }
        miningLevels.forEach((block, level) -> {
            TagInjector.inject(BuiltInRegistries.BLOCK, level, block.get());
            TagInjector.inject(BuiltInRegistries.BLOCK, RegistryHelper.id("blocks"), block.get());
        });
    }

    /**
     * @return Returns the ore block in the set
     */
    public DropExperienceBlock getOre() {
        return ore.get();
    }

    /**
     * @return Returns the storage block from the set
     */
    public Block getStorageBlock() {
        return storageBlock.get();
    }

    /**
     * @return Returns the ore storage block from the set
     */
    public Block getOreStorageBlock() {
        return oreStorageBlock.get();
    }

    /**
     * @param variant The string of the ore variants name
     * @return Returns the specified ore variant from the variant map in the blockset
     */
    public DropExperienceBlock getOreVariant(String variant) {
        return oreVariants.get(variant).get();
    }

    /**
     * @return Returns the anvil from the set
     */
    public AnvilBlock getAnvil() {
        return anvil.get();
    }

    public Set<Block> getOreVariants() {
        return oreVariants.values().stream().map(DeferredHolder::get).collect(Collectors.toUnmodifiableSet());
    }

    public Map<String, Block> getOreVariantsMap() {
        return oreVariants.entrySet().stream().collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, e -> e.getValue().get()));
    }

    public String getName() {
        return this.name;
    }

    /**
     * This is the BlockSet Builder, which is used for constructing new sets of blocks.
     * <p>
     * To begin creating BlockSets you want to call:
     * {@code public static final BlockSet SETNAME = }{@link Builder#begin(String, boolean) BlockSet.Builder.begin()}
     * where you provide a {@code string} for the name/key, and the {@code fireproof} boolean.
     * <p>
     * When creating blocks it's important to call {@link #strength(float)} before creating a block or any set.
     * This is because the values are grabbed from this method. You can call it multiple times if you wish to
     * specifically tailor the values for individual blocks.
     * <p>
     * When you are finished with adding your blocks to the set,
     * call {@link Builder#finish() Builder.finish} when you are done.
     * If you need any examples on how to apply this builder in practice, see {@link MythicBlocks}.
     *
     * @author glisco
     * @author Noaaan
     * @see Builder#begin(String, boolean)
     * @see MythicBlocks
     */
    public static class Builder {

        private static final List<BlockSet> toBeRegistered = new ArrayList<>();

        private final String name;
        private final boolean fireproof;
        private final Map<String, DeferredBlock<? extends DropExperienceBlock>> oreVariants = new LinkedHashMap<>();
        private DeferredBlock<? extends DropExperienceBlock> ore = null;
        private DeferredBlock<? extends Block> storageBlock = null;
        private DeferredBlock<? extends Block> oreStorageBlock = null;
        private DeferredBlock<? extends AnvilBlock> anvil = null;
        private SoundType currentSounds = SoundType.STONE;
        private float currentHardness = -1;
        private float currentResistance = -1;
        private final Multimap<DeferredBlock<?>, ResourceLocation> miningLevels = HashMultimap.create();
        private final Multimap<DeferredBlock<?>, ResourceLocation> anvilMap = HashMultimap.create();
        private final Consumer<BlockBehaviour.Properties> settingsProcessor = settings -> {
        };

        private final ResourceLocation SHOVEL = ResourceLocation.withDefaultNamespace("mineable/shovel");
        private final ResourceLocation PICKAXE = ResourceLocation.withDefaultNamespace("mineable/pickaxe");
        private boolean uncommon = false;

        /**
         * @see #begin(String, boolean)
         */
        private Builder(String name, boolean fireproof) {
            this.name = name;
            this.fireproof = fireproof;
        }

        /**
         * This method begins the creation of a block set.
         * You can add as many blocks as you want in the set
         * Call {@link Builder#finish()} when you are done.
         *
         * @param name      The name of the new block set
         * @param fireproof Boolean of whether the entire set should be fireproof
         */
        public static Builder begin(String name, boolean fireproof) {
            return new Builder(name, fireproof);
        }

        public static void register() {
            toBeRegistered.forEach(blockSet -> {
                MythicBlocks.BLOCKSET_MAP.put(blockSet.name, blockSet);
                blockSet.register();
            });
            toBeRegistered.clear();
        }

        /**
         * Used internally for configuring blocks
         *
         * @param hardness   Determines the breaking time of the block.
         * @param resistance Determines blast resistance of a block.
         * @param sounds     Determines the sounds that blocks play when interacted with.
         */
        public static BlockBehaviour.Properties blockSettings(float hardness, float resistance, SoundType sounds) {
            return BlockBehaviour.Properties.of()
                .strength(hardness, resistance)
                .sound(sounds)
                .forceSolidOn()
                .requiresCorrectToolForDrops();
        }

        /**
         * Puts an ore, a storage block, an ore storage block, and an anvil in the blockset.
         *
         * @param strength    Sets the strength of the blocks in the set.
         * @param miningLevel Mining level of the blocks. The ore sets the raw value,
         *                    while every other block recieves + 1 to their level.
         * @see #strength(float)    Strength
         */
        public Builder createDefaultSet(float strength, ResourceLocation miningLevel, ResourceLocation higherMiningLevel) {
            return strength(strength)
                .createOre(miningLevel)
                .strength(strength + 1.0F)
                .createOreStorageBlock(miningLevel)
                .createStorageBlock(higherMiningLevel)
                .createAnvil(miningLevel);
        }

        /**
         * Puts an ore, a storage block, and an ore storage block in the blockset.
         *
         * @param strength           Sets the strength of the blocks in the set.
         * @param miningLevel        The mining level of the ore block
         * @param storageMiningLevel The mining level of both storage blocks
         * @see #strength(float)
         */
        public Builder createBlockSet(float strength, ResourceLocation miningLevel, ResourceLocation storageMiningLevel) {
            return strength(strength)
                .createOre(miningLevel)
                .strength(strength + 1.0F)
                .createStorageBlock(storageMiningLevel)
                .createOreStorageBlock(storageMiningLevel);
        }

        /**
         * Puts an ore, a storage block and an ore storage block in the set, with slightly more configurable settings.
         *
         * @param oreStrength        The strength of the ore block.
         * @param oreMiningLevel     The mining level of the ore block.
         * @param storageStrength    The strength of the storage block and ore storage block.
         * @param storageMiningLevel The mining level of the storage block and ore storage block.
         * @see #strength(float)        oreStrength and storageStrength
         */
        public Builder createDefaultSet(float oreStrength, ResourceLocation oreMiningLevel, float storageStrength, ResourceLocation storageMiningLevel) {
            return strength(oreStrength)
                .createOre(oreMiningLevel)
                .strength(storageStrength)
                .createStorageBlock(storageMiningLevel)
                .createOreStorageBlock(storageMiningLevel);
        }

        public Builder createAquariumSet(float oreStrength, ResourceLocation oreMiningLevel, float storageStrength, ResourceLocation storageMiningLevel) {
            return strength(oreStrength)
                    .createOre(oreMiningLevel)
                    .strength(storageStrength)
                    .createAquariumStorageBlock(storageMiningLevel)
                    .createOreStorageBlock(storageMiningLevel);
        }

        /**
         * Puts a storage block and an anvil in the blockset.
         *
         * @param miningLevel The mining level of the anvil and the storage block
         * @see #strength(float)
         */
        public Builder createAnvilSet(float strength, ResourceLocation miningLevel) {
            return strength(strength)
                .sounds(SoundType.METAL)
                .createStorageBlock(miningLevel)
                .createAnvil(miningLevel);
        }

        /**
         * Puts a storage block and an anvil in the blockset, where the storage block is configurable.
         *
         * @param hardness    The hardness of the storage block.
         * @param resistance  The blast resistance of the storage block.
         * @param miningLevel The mining level of the anvil and the storage block.
         * @see #createAnvil(ResourceLocation)  createAnvil
         */
        public Builder createAnvilSet(float hardness, float resistance, ResourceLocation miningLevel) {
            return strength(hardness, resistance)
                .createStorageBlock(this.currentSounds, miningLevel)
                .createAnvil(miningLevel);
        }

        /**
         * Applies sounds to the block(s) in the set.
         *
         * @param sounds The {@link SoundType} which should be played.
         */
        public Builder sounds(SoundType sounds) {
            this.currentSounds = sounds;
            return this;
        }

        /**
         * A simplified method to create a hardness and resistance value from a single int.
         *
         * @param strength The base int value for the blocks' strength.
         * @return hardness, resistance (strength + 1)
         */
        public Builder strength(float strength) {
            return strength(strength, strength + 1);
        }

        /**
         * Gives the block(s) in the set the specified strength.
         *
         * @param hardness   Hardness of the block, determines breaking speed.
         * @param resistance Blast resistance of the block.
         */
        public Builder strength(float hardness, float resistance) {
            this.currentHardness = hardness;
            this.currentResistance = resistance;
            return this;
        }

        /**
         * Creates an ore block.
         *
         * @param miningLevel The mining level of the ore block.
         * @see Builder
         */
        public Builder createOre(ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.ore = RegistryHelper.block(
                    name + "_ore",
                    () -> new DropExperienceBlock(ConstantInt.ZERO, settings),
                    fireproof,
                    uncommon
            );
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * Creates an ore block, which drops experience.
         *
         * @param miningLevel The mining level of the ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         * @see Builder
         */
        public Builder createOre(ResourceLocation miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.ore = RegistryHelper.block(
                    name + "_ore",
                    () -> new DropExperienceBlock(experience, settings),
                    fireproof,
                    uncommon
            );
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * Creates an ore block, which drops experience.
         *
         * @param miningLevel The mining level of the ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         * @see Builder
         */
        public Builder createLuminantOre(ResourceLocation miningLevel, UniformInt experience, int luminance) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds).lightLevel(blockState -> luminance);
            settingsProcessor.accept(settings);
            this.ore = RegistryHelper.block(
                    name + "_ore",
                    () -> new DropExperienceBlock(experience, settings), // ConstantInt.ZERO?
                    fireproof,
                    uncommon
            );
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            //Maldenhagen.injectCopium(this.ore); TODO: Fix maldgenhagen
            return this;
        }

        /**
         * Creates an ore variant.
         *
         * @param name        The name/key for the variant.
         * @param miningLevel The mining level of the ore variant.
         * @see Builder
         */
        public Builder createOreVariant(String name, ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name,
                    RegistryHelper.block(
                            name + "_" + this.name + "_ore",
                            () -> new DropExperienceBlock(ConstantInt.ZERO, settings),
                            fireproof,
                            uncommon
                    )
            );
            miningLevels.put(oreVariants.get(name), miningLevel);
            miningLevels.put(oreVariants.get(name), PICKAXE);
            return this;
        }

        /**
         * Creates an ore variant, which drops experience.
         *
         * @param name        The name/key for the variant.
         * @param miningLevel The mining level of the variant ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createOreVariant(String name, ResourceLocation miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name,
                    RegistryHelper.block(
                            name + "_" + this.name + "_ore",
                            () -> new DropExperienceBlock(experience, settings),
                            fireproof,
                            uncommon
                    )
            );
            miningLevels.put(oreVariants.get(name), miningLevel);
            miningLevels.put(oreVariants.get(name), PICKAXE);
            return this;
        }

        /**
         * Creates an ore variant, which drops experience.
         *
         * @param name        The name/key for the variant.
         * @param miningLevel The mining level of the variant ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createOreVariant(String name, ResourceLocation miningLevel, UniformInt experience, int luminance) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds).lightLevel(blockState -> luminance);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name,
                    RegistryHelper.block(
                            name + "_" + this.name + "_ore",
                            () -> new DropExperienceBlock(experience, settings),
                            fireproof,
                            uncommon
                    )
            );
            miningLevels.put(oreVariants.get(name), miningLevel);
            miningLevels.put(oreVariants.get(name), PICKAXE);

            //Maldenhagen.injectCopium(this.oreVariants.get(name)); TODO: Fix maldgenhagen
            return this;
        }

        /**
         * A special ore creator for the creation of a {@link StarriteOreBlock}.
         *
         * @param miningLevel The mining level of the block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createStarriteOre(ResourceLocation miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.ore = RegistryHelper.block(
                    name + "_ore",
                    () -> new StarriteOreBlock(settings, experience),
                    fireproof,
                    uncommon);
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * A special ore creator for the creation of a {@link BanglumOreBlock}.
         *
         * @param miningLevel The mining level of the block.
         */
        public Builder createBanglumOre(ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.ore = RegistryHelper.block(
                    name + "_ore",
                    () -> new BanglumOreBlock(settings),
                    fireproof,
                    uncommon
            );
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * A special method for the creation of variants from {@link StarriteOreBlock}.
         *
         * @param name        The name/key for the variant.
         * @param miningLevel The mining level of the block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createStarriteOreVariant(String name, ResourceLocation miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name, RegistryHelper.block(
                    name + "_" + this.name + "_ore",
                    () -> new StarriteOreBlock(settings, experience),
                    fireproof,
                    uncommon
            ));
            miningLevels.put(oreVariants.get(name), miningLevel);
            miningLevels.put(oreVariants.get(name), PICKAXE);
            return this;
        }

        /**
         * A special method for the creation of variants from {@link BanglumOreBlock}.
         *
         * @param name        The name/key for the variant.
         * @param miningLevel The mining level of the block.
         */
        public Builder createBanglumOreVariant(String name, ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name, RegistryHelper.block(
                    name + "_" + this.name + "_ore",
                    () -> new BanglumOreBlock(settings),
                    fireproof,
                    uncommon
            ));
            miningLevels.put(oreVariants.get(name), miningLevel);
            miningLevels.put(oreVariants.get(name), PICKAXE);
            return this;
        }

        /**
         * A special method for the creation of storage blocks that copies Amethyst Blocks.
         *
         * @param miningLevel The mining level of the block.
         * @see net.minecraft.world.level.block.Blocks#AMETHYST_BLOCK
         * @see AmethystBlock
         */
        public Builder createAmethystStorageBlock(ResourceLocation miningLevel) {
            this.storageBlock = RegistryHelper.block(
                    name + "_block",
                    () -> new AmethystBlock(blockSettings(currentHardness, currentResistance, SoundType.AMETHYST)),
                    fireproof,
                    uncommon
            );
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        /**
         * Create a storage block, with a specific material in mind.
         *
         * @param miningLevel The mining level of the storage block.
         */
        public Builder createStorageBlock(ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.storageBlock = RegistryHelper.block(
                    name + "_block",
                    () -> new Block(settings),
                    fireproof,
                    uncommon
            );
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        public Builder createAquariumStorageBlock(ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.storageBlock = RegistryHelper.block(
                    name + "_block",
                    () -> new Block(settings){
                        @Override
                        public boolean isConduitFrame(BlockState state, LevelReader level, BlockPos pos, BlockPos conduit) {
                            return true;
                        }
                    },
                    fireproof,
                    uncommon
            );
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        /**
         * Create a storage block, with a specific sound in mind.
         *
         * @param sounds      A {@link SoundType}, which determines block sounds.
         * @param miningLevel The mining level of the storage block.
         */
        public Builder createStorageBlock(SoundType sounds, ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, sounds);
            settingsProcessor.accept(settings);
            this.storageBlock = RegistryHelper.block(
                    name + "_block",
                    () -> new Block(settings),
                    fireproof,
                    uncommon
            );
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        /**
         * Create a raw ore storage block.
         *
         * @param miningLevel The mining level of the raw storage block.
         */
        public Builder createOreStorageBlock(ResourceLocation miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settingsProcessor.accept(settings);
            this.oreStorageBlock = RegistryHelper.block(
                    "raw_" + name + "_block",
                    () -> new Block(settings),
                    fireproof,
                    uncommon
            );
            miningLevels.put(oreStorageBlock, miningLevel);
            miningLevels.put(oreStorageBlock, PICKAXE);
            return this;
        }

        /**
         * Creates an anvil for a blockset.
         * Only requires a mining level, since hardness and resistance match vanilla values.
         *
         * @param miningLevel Mining level of the anvil.
         */
        public Builder createAnvil(ResourceLocation miningLevel) {
            if (MythicMetals.CONFIG.enableAnvils()) {
                final var settings = blockSettings(5.0f, 15000f, SoundType.ANVIL);
                settingsProcessor.accept(settings);
                this.anvil = RegistryHelper.block(
                        name + "_anvil",
                        () -> new AnvilBlock(settings),
                        fireproof,
                        uncommon
                );
                anvilMap.put(anvil, miningLevel);
                anvilMap.put(anvil, PICKAXE);
            }
            return this;
        }

        /**
         * Kinda manual at this point ngl
         */
        public <T extends Block> Builder createCustomStorageBlock(Supplier<T> block, ResourceLocation miningLevel) {
            this.storageBlock = RegistryHelper.block(
                    name + "_block",
                    block,
                    fireproof,
                    uncommon
            );
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        public Builder createCustomStorageBlock(ResourceLocation miningLevel, BlockBehaviour.Properties settings) {
            settingsProcessor.accept(settings);
            this.storageBlock = RegistryHelper.block(
                    name + "_block",
                    () -> new Block(settings),
                    fireproof,
                    uncommon
            );
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        public Builder uncommon() {
            this.uncommon = true;
            return this;
        }

        /**
         * Finishes the creation of the block set, and returns the entire set using the settings declared.
         * For registering the blocks call {@link Builder#register() Builder.register} during mod initialization.
         *
         * @return BlockSet
         */
        public BlockSet finish() {
            final var set = new BlockSet(this.name, this.ore,
                this.storageBlock, this.oreStorageBlock, this.anvil,
                this.oreVariants, this.fireproof, this.miningLevels, this.anvilMap, this.uncommon);
            Builder.toBeRegistered.add(set);
            return set;
        }
    }
}
