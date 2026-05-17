package com.mythicmetals.registry;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
//import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegisterSounds /*implements AutoRegistryContainer<SoundEvent>*/ {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, MythicMetals.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_RUNITE = RegistryHelper.soundEvent("equip_runite", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_runite")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_ADAMANTITE = RegistryHelper.soundEvent("equip_adamantite", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_adamantite")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_AQUARIUM = RegistryHelper.soundEvent("equip_aquarium", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_aquarium")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_BANGLUM = RegistryHelper.soundEvent("equip_banglum", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_banglum")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_BRONZE = RegistryHelper.soundEvent("equip_bronze", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_bronze")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_CARMOT = RegistryHelper.soundEvent("equip_carmot", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_carmot")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_CELESTIUM = RegistryHelper.soundEvent("equip_celestium", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_celestium")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_CELESTIUM_ELYTRA = RegistryHelper.soundEvent("equip_celestium_elytra", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_celestium_elytra")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_COPPER = RegistryHelper.soundEvent("equip_copper", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_copper")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_DURASTEEL = RegistryHelper.soundEvent("equip_durasteel", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_durasteel")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_HALLOWED = RegistryHelper.soundEvent("equip_hallowed", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_hallowed")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_KYBER = RegistryHelper.soundEvent("equip_kyber", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_kyber")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_LEGENDARY_BANGLUM = RegistryHelper.soundEvent("equip_legendary_banglum", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_legendary_banglum")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_METALLURGIUM = RegistryHelper.soundEvent("equip_metallurgium", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_metallurgium")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_MIDAS_GOLD = RegistryHelper.soundEvent("equip_midas_gold", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_midas_gold")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_MYTHRIL = RegistryHelper.soundEvent("equip_mythril", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_mythril")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_ORICHALCUM = RegistryHelper.soundEvent("equip_orichalcum", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_orichalcum")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_OSMIUM_CHAINMAIL = RegistryHelper.soundEvent("equip_osmium_chainmail", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_osmium_chainmail")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_OSMIUM = RegistryHelper.soundEvent("equip_osmium", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_osmium")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_PALLADIUM = RegistryHelper.soundEvent("equip_palladium", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_palladium")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_PROMETHEUM = RegistryHelper.soundEvent("equip_prometheum", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_prometheum")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_SILVER = RegistryHelper.soundEvent("equip_silver", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_silver")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_STAR_PLATINUM = RegistryHelper.soundEvent("equip_star_platinum", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_star_platinum")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_STEEL = RegistryHelper.soundEvent("equip_steel", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_steel")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_STORMYX = RegistryHelper.soundEvent("equip_stormyx", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_stormyx")));
    public static final DeferredHolder<SoundEvent, SoundEvent> EQUIP_TIDESINGER = RegistryHelper.soundEvent("equip_tidesinger", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_tidesinger")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MORKITE_ORE_BREAK = RegistryHelper.soundEvent("morkite_ore_break", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("morkite_ore_break")));
    public static final DeferredHolder<SoundEvent, SoundEvent> DEEPSLATE_MORKITE_ORE_BREAK = RegistryHelper.soundEvent("deepslate_morkite_ore_break", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("deepslate_morkite_ore_break")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PROJECTILE_BARRIER_BEGIN = RegistryHelper.soundEvent("projectile_barrier_begin", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("projectile_barrier_begin")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PROJECTILE_BARRIER_MAINTAIN = RegistryHelper.soundEvent("projectile_barrier_maintain", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("projectile_barrier_maintain")));
    public static final DeferredHolder<SoundEvent, SoundEvent> PROJECTILE_BARRIER_END = RegistryHelper.soundEvent("projectile_barrier_end", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("projectile_barrier_end")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BANGLUM_NUKE_IGNITE = RegistryHelper.soundEvent("banglum_nuke_ignite", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("banglum_nuke_ignite")));
    public static final DeferredHolder<SoundEvent, SoundEvent> BANGLUM_NUKE_EXPLOSION = RegistryHelper.soundEvent("banglum_nuke_explosion", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("banglum_nuke_explosion")));
    public static final DeferredHolder<SoundEvent, SoundEvent> MELODY = RegistryHelper.soundEvent("melody", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("melody")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CARMOT_BELL_RING = RegistryHelper.soundEvent("carmot_bell_ring", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("carmot_bell_ring")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CARMOT_BELL_DING = RegistryHelper.soundEvent("carmot_bell_ding", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("carmot_bell_ding")));
    public static final DeferredHolder<SoundEvent, SoundEvent> CARMOT_BELL_DING_PLAIN = RegistryHelper.soundEvent("carmot_bell_ding_plain", () -> SoundEvent.createVariableRangeEvent(RegistryHelper.id("carmot_bell_ding_plain")));

    public static final SoundType MORKITE_ORE = new DeferredSoundType(1.0F, 1.0F,
        MORKITE_ORE_BREAK,
            () -> SoundEvents.DRIPSTONE_BLOCK_STEP,
            () -> SoundEvents.DRIPSTONE_BLOCK_PLACE,
            () -> SoundEvents.DRIPSTONE_BLOCK_HIT,
            () -> SoundEvents.DRIPSTONE_BLOCK_FALL);
    public static final SoundType DEEPSLATE_MORKITE_ORE = new DeferredSoundType(1.0F, 1.0F,
        DEEPSLATE_MORKITE_ORE_BREAK,
            () -> SoundEvents.DEEPSLATE_STEP,
            () -> SoundEvents.DEEPSLATE_PLACE,
            () -> SoundEvents.DEEPSLATE_HIT,
            () -> SoundEvents.DEEPSLATE_FALL);

    public static void init() {

    }

    /*
    @Override
    public Registry<SoundEvent> getRegistry() {
        return BuiltInRegistries.SOUND_EVENT;
    }

    @Override
    public Class<SoundEvent> getTargetFieldType() {
        return SoundEvent.class;
    }
     */
}

