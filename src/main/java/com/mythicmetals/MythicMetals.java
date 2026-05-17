package com.mythicmetals;

import com.google.common.base.Suppliers;
import com.mythicmetals.ability.Abilities;
import com.mythicmetals.armor.*;
import com.mythicmetals.block.BanglumNukeHandler;
import com.mythicmetals.block.BlockSet;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.client.MythicMetalsClient;
import com.mythicmetals.command.MythicCommands;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.conditions.MythicResourceConditions;
import com.mythicmetals.config.MythicMetalsConfig;
import com.mythicmetals.data.MythicOreFeatures;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.entity.*;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.MythicPotions;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.*;
import com.mythicmetals.recipe.MythicRecipeSerializers;
import com.mythicmetals.registry.*;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
//import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
//import net.fabricmc.api.ModInitializer;
//import net.fabricmc.fabric.api.registry.FuelRegistry;
//import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(MythicMetals.MOD_ID)
@EventBusSubscriber
public class MythicMetals /*implements ModInitializer*/ {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final int CONFIG_VERSION = 13;

    public static final AbstractMinecart.Type BANGLUM_TNT = Enum.valueOf(AbstractMinecart.Type.class, "BANGLUM_TNT");
    public static final AbstractMinecart.Type PALLADIUM_MINECART = Enum.valueOf(AbstractMinecart.Type.class, "PALLADIUM_MINECART");

    public static MythicMetalsConfig CONFIG = MythicMetalsConfig.createAndLoad();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, OwoItemGroup> TABBED_GROUP = CREATIVE_MODE_TABS.register("main", () -> OwoItemGroup.builder(RegistryHelper.id("main"), () -> Icon.of(MythicItems.STORMYX.getIngot()))
        .initializer(group -> {
            group.addTab(Icon.of(MythicItems.ADAMANTITE.getIngot()), "items", TagKey.create(Registries.ITEM, RegistryHelper.id("item_tab")), false);
            group.addTab(Icon.of(MythicBlocks.ADAMANTITE.getStorageBlock()), "blocks", TagKey.create(Registries.ITEM, RegistryHelper.id("blocks")), false);
            group.addTab(Icon.of(MythicTools.ADAMANTITE.getPickaxe()), "tools", TagKey.create(Registries.ITEM, RegistryHelper.id("tool_tab")), false);
            group.addTab(Icon.of(MythicArmor.ADAMANTITE.getChestplate()), "armor", TagKey.create(Registries.ITEM, RegistryHelper.id("armor_tab")), false);
            group.addButton(ItemGroupButton.github(group, "https://github.com/Noaaan/MythicMetals/issues"));
            group.addButton(ItemGroupButton.curseforge(group, "https://www.curseforge.com/minecraft/mc-mods/mythicmetals"));
            group.addButton(ItemGroupButton.modrinth(group, "https://modrinth.com/mod/mythicmetals"));
            group.addButton(ItemGroupButton.discord(group, "https://discord.gg/69cKvQWScC"));
        })
        .build());
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);

    public static final Supplier<AttachmentType<CarmotShield>> CARMOT_SHIELD = ATTACHMENT_TYPES.register("carmot_shield", () -> AttachmentType
            .serializable(iAttachmentHolder ->  iAttachmentHolder instanceof Player player ?  new CarmotShield(player) : null)
            .sync(new CarmotShield.SyncHandler())
            .copyOnDeath()
            .build());
    public static final Supplier<AttachmentType<CombustionCooldown>> COMBUSTION_COOLDOWN = ATTACHMENT_TYPES.register("combustion_cooldown", () -> AttachmentType
            .serializable(iAttachmentHolder ->  iAttachmentHolder instanceof LivingEntity livingEntity ?  new CombustionCooldown(livingEntity) : null)
            .sync(new CombustionCooldown.SyncHandler())
            //.copyOnDeath()
            .build());

    public MythicMetals(IEventBus modEventBus, ModContainer modContainer) {
        RegisterSounds.SOUND_EVENTS.register(modEventBus);
        RegisterSounds.init();
        MythicItems.ITEMS.register(modEventBus);
        MythicItems.init();
        MythicItems.Mats.init();
        MythicItems.Templates.init();
        if (!FMLEnvironment.production) {
            MythicItems.ParticleSticks.init();
        }
        MythicItems.Copper.init();
        MythicArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        MythicArmorMaterials.init();
        MythicArmor.init();
        MythicTools.init();
        MythicTools.Frogery.init();
        RegisterBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        RegisterBlockEntityTypes.init();
        MythicCommands.ARGUMENT_TYPES.register(modEventBus);
        //MythicCommands.init();
        MythicBlocks.BLOCKS.register(modEventBus);
        MythicBlocks.init();
        MythicDataComponents.DATA_COMPONENTS.register(modEventBus);
        MythicDataComponents.init();
        MythicPotions.POTIONS.register(modEventBus);
        MythicPotions.init();
        RegisterPointOfInterests.POI_TYPES.register(modEventBus);
        RegisterPointOfInterests.init();
        MythicEntityAttributes.ATTRIBUTES.register(modEventBus);
        MythicEntityAttributes.init();
        MythicEntities.ENTITY_TYPES.register(modEventBus);
        MythicEntities.init();
        RegisterLootConditions.LOOT_CONDITION_TYPES.register(modEventBus);
        RegisterLootConditions.init();
        MythicStatusEffects.MOB_EFFECTS.register(modEventBus);
        MythicStatusEffects.init();
        MythicRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        MythicRecipeSerializers.init();
        RegisterCriteria.TRIGGER_TYPES.register(modEventBus);
        RegisterCriteria.init();

        CREATIVE_MODE_TABS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);

        modEventBus.addListener((FMLCommonSetupEvent event) -> {
            this.onInitialize();
        });
        if(FMLEnvironment.dist == Dist.CLIENT){
            modEventBus.addListener((FMLClientSetupEvent event) -> {
                event.enqueueWork(() -> new MythicMetalsClient().onInitializeClient());
            });
        }
    }

    //@Override
    public void onInitialize() {
        //FieldRegistrationHandler.register(RegisterSounds.class, MOD_ID, false);
        //FieldRegistrationHandler.processSimple(MythicItems.class, false);
        //FieldRegistrationHandler.register(MythicItems.Mats.class, MOD_ID, false);
        //FieldRegistrationHandler.register(MythicItems.Templates.class, MOD_ID, false);
        if (!FMLEnvironment.production) {
            //FieldRegistrationHandler.register(MythicItems.ParticleSticks.class, MOD_ID, false);
        }
        //FieldRegistrationHandler.processSimple(MythicItems.Copper.class, false);
        //FieldRegistrationHandler.register(MythicArmorMaterials.class, MOD_ID, false);
        //FieldRegistrationHandler.processSimple(MythicTools.class, true);
        //FieldRegistrationHandler.processSimple(MythicArmor.class, false);
        //FieldRegistrationHandler.register(RegisterBlockEntityTypes.class, MOD_ID, false);
        MythicParticleSystem.init();
        BlockSet.Builder.register();
        BanglumNukeHandler.init();
        MythicOreFeatures.init();
        MythicCommands.init();
        MythicCommands.registerCommands();
        Abilities.init();
        TABBED_GROUP.get().initialize();
        /*
        FuelRegistry.INSTANCE.add(MythicItems.Mats.MORKITE, 1200);
        FuelRegistry.INSTANCE.add(MythicBlocks.MORKITE.getStorageBlock(), 12800);
         */
        MythicResourceConditions.init();
        //FieldRegistrationHandler.processSimple(RegisterCriteria.class, false);
        BlockBreaker.initHammerTime();
        MythicLootOps.init();
        /*
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 5, factories -> {
            factories.add(new VillagerTrades.SellItemFactory(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE, 48, 1, 2, 30));
        });
         */
        registerDispenserBehaviour();

        if (CONFIG.configVersion() < CONFIG_VERSION) {
            for (int i = 0; i < 5; i++) {
                LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete it so it can be re-generated.");
            }
        }

        if (ModList.get().isLoaded("harvest_scythes")) {
            LOGGER.info("[Mythic Metals] I see HarvestScythes. I'll take care of DH so you don't have to");
        }
        if (ModList.get().isLoaded("enhancedcraft")) {
            LOGGER.info("[Mythic Metals] Oh EnhancedCraft? If you ever see Spxctre tell him I said hi!");
        }
        if (ModList.get().isLoaded("origins")) {
            LOGGER.info("[Mythic Metals] Have fun using Origins!");
        }
        if (ModList.get().isLoaded("spectrum")) {
            LOGGER.info("[Mythic Metals] Spectrum is loaded! Good luck on finding all of its secrets...");
        }
        if (ModList.get().isLoaded("jello")) {
            LOGGER.info("[Mythic Metals] Is that Jello? Here comes the colors, weeeeeee!");
        }
        if (ModList.get().isLoaded("terralith")) {
            LOGGER.info("[Mythic Metals] Terralith detected. Many ores can spawn in unexpected ways due to the new overworld. Modpack devs, take note of this");
        }
        if (ModList.get().isLoaded("ftb-chunks-fabric") || ModList.get().isLoaded("ftb-chunks-neoforge")) {
            if (!ModList.get().isLoaded("ftb-xmod-compat-fabric")) {
                for (int i = 0; i < 3; i++) {
                    LOGGER.error("[Mythic Metals] FTB Chunks is loaded but FTB XMod Compat Fabric addon is not. This means claim protection will not work for some items!");
                }
            }
        }
        LOGGER.info("[Mythic Metals] Mythic Metals is now initialized.");
    }

    @SubscribeEvent
    static void onFurnaceFuelTimeEvent(FurnaceFuelBurnTimeEvent event) {
        if(event.getItemStack().is(MythicItems.Mats.MORKITE)){
            event.setBurnTime(1200);
        } else if(event.getItemStack().is(MythicBlocks.MORKITE.getStorageBlock().asItem())){
            event.setBurnTime(12800);
        }
    }

    private void registerDispenserBehaviour() {
        DispenserBlock.registerBehavior(() -> MythicTools.STAR_PLATINUM_ARROW.get(), new ProjectileDispenseBehavior(MythicTools.STAR_PLATINUM_ARROW.get()));
        DispenserBlock.registerBehavior(() -> MythicTools.RUNITE_ARROW.get(), new ProjectileDispenseBehavior(MythicTools.RUNITE_ARROW.get()));
        DispenserBlock.registerBehavior(() -> MythicTools.TIPPED_RUNITE_ARROW.get(), new ProjectileDispenseBehavior(MythicTools.TIPPED_RUNITE_ARROW.get()));
    }


    /*
    @SubscribeEvent
    static void registerEntityComponentFactories(RegisterCapabilitiesEvent registry) {
        for (EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE) {
            registry.registerEntity(COMBUSTION_COOLDOWN, entityType, (o, unused) -> new CombustionCooldown(o instanceof LivingEntity le ? le : null));
        }
        //registry.re(LivingEntity.class, COMBUSTION_COOLDOWN, CombustionCooldown::new);
        registry.registerEntity(CARMOT_SHIELD, EntityType.PLAYER, (o, unused) -> new CarmotShield(o));
        //registry.registerForPlayers(CARMOT_SHIELD, CarmotShield::new, RespawnCopyStrategy.INVENTORY);
    }
     */

    @SubscribeEvent
    static void registerVillagerTrades(VillagerTradesEvent event) {
        /*
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 5, factories -> {
            factories.add(new VillagerTrades.SellItemFactory(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE, 48, 1, 2, 30));
        });
         */
        if(event.getType() == VillagerProfession.CLERIC){
            event.getTrades().get(5).add(new VillagerTrades.ItemsForEmeralds(MythicItems.Templates.AEGIS_SMITHING_TEMPLATE.get(), 48, 1, 2, 30));
        }
    }@SubscribeEvent
    static void onEntiyAttributeModification(EntityAttributeModificationEvent event) {
        for(EntityType<? extends LivingEntity> entityType : event.getTypes()){
            event.add(entityType, MythicEntityAttributes.CARMOT_SHIELD);
            event.add(entityType, MythicEntityAttributes.FIRE_VULNERABILITY);
            event.add(entityType, MythicEntityAttributes.ELYTRA_ROCKET_SPEED);
        }
    }

}
