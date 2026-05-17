package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Consumer;

public class ItemSet {
    private final DeferredItem<Item> ingotItem;
    private DeferredItem<Item> rawOreItem = null;
    private DeferredItem<Item> nuggetItem = null;
    private DeferredItem<Item> dustItem = null;
    private boolean requiresBlasting = false;
    // Used for smelting recipes during datagen
    private final float xp;
    private final String name;

    private static Item.Properties createSettings(Consumer<Item.Properties> settingsProcessor) {
        final var settings = new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(0);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ItemSet(String name, float xp) {
        this(name, false, false, xp, settings -> {
        }, false);
    }

    public ItemSet(String name,float xp, boolean requiresBlasting) {
        this(name, false, requiresBlasting, xp, settings -> {
        }, false);
    }

    public ItemSet(String name, boolean isAlloy, boolean isStarPlatinum) {
        this(name, isAlloy, true, 0.1f, settings -> {
        }, isStarPlatinum);
    }

    public ItemSet(String name,boolean isAlloy) {
        this(name, isAlloy, true, 0.1f, settings -> {
        }, false);
    }

    public ItemSet(String name,boolean isAlloy, float xp) {
        this(name, isAlloy, false, xp, settings -> {
        }, false);
    }

    public ItemSet(String name,boolean isAlloy, float xp, boolean requiresBlasting) {
        this(name, isAlloy, requiresBlasting, xp, settings -> {
        }, false);
    }

    public ItemSet(String name,boolean isAlloy, boolean requiresBlasting, Consumer<Item.Properties> settingsConsumer) {
        this(name, isAlloy, requiresBlasting, 0.1f, settingsConsumer, false);
    }

    public ItemSet(String name,boolean isAlloy, boolean requiresBlasting, float xp, Consumer<Item.Properties> settingsConsumer) {
        this(name, isAlloy, requiresBlasting, xp, settingsConsumer, false);
    }

    public ItemSet(String name, boolean isAlloy, boolean requiresBlasting, float xp, Consumer<Item.Properties> settingsConsumer, boolean isStarPlatinum) {
        this.name = name;
        this.ingotItem = MythicItems.ITEMS.register(
                isStarPlatinum ? name : name + "_ingot",
                () -> makeItem(createSettings(settingsConsumer))
        );
        if (!isAlloy) {
            this.rawOreItem = MythicItems.ITEMS.register(
                    "raw_" + name,
                    () -> makeItem(createSettings(settingsConsumer))
            );
        }
        if (MythicMetals.CONFIG.enableNuggets()) {
            this.nuggetItem = MythicItems.ITEMS.register(
                    name + "_nugget",
                    () -> makeItem(createSettings(settingsConsumer))
            );
        }
        if (MythicMetals.CONFIG.enableDusts()) {
            this.dustItem = MythicItems.ITEMS.register(
                    name + "_dust",
                    () -> makeItem(createSettings(settingsConsumer))
            );
        }
        this.xp = xp;
        this.requiresBlasting = requiresBlasting;
    }

    public void register(String name) {
        /*
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_ingot"), ingotItem);
        if (rawOreItem != null) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id("raw_" + name), rawOreItem);
        }
        if (nuggetItem != null) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
            // Conditionally add nuggets to nuggets tag
            TagInjector.inject(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets"), nuggetItem);
        }
        if (dustItem != null) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_dust"), dustItem);
        }
         */
    }

    public void register(String name, boolean imStarPlatinum) {
        /*
        if (imStarPlatinum) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name), ingotItem);
            if (nuggetItem != null) {
                Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
            }
            if (dustItem != null) {
                Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_dust"), dustItem);
            }
        } else {
            register(name);
        }
         */

    }

    protected Item makeItem(Item.Properties settings) {
        return new Item(settings);
    }

    public Item getRawOre() {
        return rawOreItem.get();
    }

    public Item getIngot() {
        return ingotItem.get();
    }

    public Item getNugget() {
        return nuggetItem.get();
    }

    public Item getDust() {
        return dustItem.get();
    }

    public boolean requiresBlasting() {
        return requiresBlasting;
    }

    public float getXp() {
        return this.xp > 0 ? this.xp : 0.0f;
    }
}
