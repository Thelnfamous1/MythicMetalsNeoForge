package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Consumer;

public class CopperSet {
    private DeferredItem<Item> nuggetItem = null;
    private DeferredItem<Item> dustItem = null;

    private static Item.Properties createSettings(Consumer<Item.Properties> settingsProcessor) {
        final var settings = new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(0);
        settingsProcessor.accept(settings);
        return settings;
    }

    public CopperSet() {
        this("copper", settings -> {
        });
    }

    public CopperSet(String name, Consumer<Item.Properties> settingsConsumer) {
        if (MythicMetals.CONFIG.enableNuggets()) {
            this.nuggetItem = RegistryHelper.item(name + "_nugget", () -> makeItem(createSettings(settingsConsumer)));
        }
        if (MythicMetals.CONFIG.enableDusts()) {
            this.dustItem = RegistryHelper.item(name + "_dust", () -> makeItem(createSettings(settingsConsumer)));
        }
    }

    public void register(String name) {
        /*
        if (nuggetItem != null) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_nugget"), nuggetItem);
        }
        if (dustItem != null) {
            Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_dust"), dustItem);
        }
         */
    }

    protected Item makeItem(Item.Properties settings) {
        return new Item(settings);
    }

    public Item getNugget() {
        return nuggetItem.get();
    }

    public Item getDust() {
        return dustItem.get();
    }
}
