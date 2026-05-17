package com.mythicmetals.entity;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MythicEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MythicMetals.MOD_ID);
    public static final DeferredHolder<EntityType<?>, EntityType<BanglumTntEntity>> BANGLUM_TNT_ENTITY_TYPE = RegistryHelper.entityType("banglum_tnt", () -> EntityType.Builder.<BanglumTntEntity>of(
            BanglumTntEntity::new, MobCategory.MISC).sized(1f, 1f).build(RegistryHelper.id("banglum_tnt").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<BanglumNukeEntity>> BANGLUM_NUKE_ENTITY_TYPE = RegistryHelper.entityType("banglum_nuke", () -> EntityType.Builder.<BanglumNukeEntity>of(
            BanglumNukeEntity::new, MobCategory.MISC).sized(3f, 3f).build(RegistryHelper.id("banglum_nuke").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<StarPlatinumArrowEntity>> STAR_PLATINUM_ARROW_ENTITY_TYPE = RegistryHelper.entityType("star_platinum_arrow", () -> EntityType.Builder.<StarPlatinumArrowEntity>of(
                    StarPlatinumArrowEntity::new, MobCategory.MISC)
            .sized(.5f, .5f)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(RegistryHelper.id("star_platinum_arrow").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<RuniteArrowEntity>> RUNITE_ARROW_ENTITY_TYPE = RegistryHelper.entityType("runite_arrow", () -> EntityType.Builder.<RuniteArrowEntity>of(
                    RuniteArrowEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(RegistryHelper.id("runite_arrow").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<BanglumTntMinecartEntity>> BANGLUM_TNT_MINECART_ENTITY_TYPE = RegistryHelper.entityType("banglum_tnt_minecart", () -> EntityType.Builder.<BanglumTntMinecartEntity>of(
                    BanglumTntMinecartEntity::new, MobCategory.MISC)
            .sized(0.98f, 0.7f)
            .clientTrackingRange(8)
            .build(RegistryHelper.id("banglum_tnt_minecart").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<PalladiumMinecartEntity>> PALLADIUM_MINECART_ENTITY_TYPE = RegistryHelper.entityType("palladium_minecart", () -> EntityType.Builder.<PalladiumMinecartEntity>of(
                    PalladiumMinecartEntity::new, MobCategory.MISC)
            .sized(0.98f, 0.7f)
            .clientTrackingRange(8)
            .fireImmune()
            .build(RegistryHelper.id("palladium_minecart").toString()));

    public static void init() {
        /*
        RegistryHelper.entityType("banglum_tnt_minecart", BANGLUM_TNT_MINECART_ENTITY_TYPE);
        RegistryHelper.entityType("palladium_minecart", PALLADIUM_MINECART_ENTITY_TYPE);
        RegistryHelper.entityType("banglum_tnt", BANGLUM_TNT_ENTITY_TYPE);
        RegistryHelper.entityType("banglum_nuke", BANGLUM_NUKE_ENTITY_TYPE);
        RegistryHelper.entityType("star_platinum_arrow", STAR_PLATINUM_ARROW_ENTITY_TYPE);
        RegistryHelper.entityType("runite_arrow", RUNITE_ARROW_ENTITY_TYPE);
         */

    }
}
