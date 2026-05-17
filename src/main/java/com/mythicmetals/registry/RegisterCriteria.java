package com.mythicmetals.registry;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.SimpleCriteria;
//import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
//import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
//import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

//import java.lang.reflect.Field;

public class RegisterCriteria /*implements SimpleFieldProcessingSubject<CriterionTrigger<?>>*/ {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGER_TYPES =
            DeferredRegister.create(Registries.TRIGGER_TYPE, MythicMetals.MOD_ID);
    public static final DeferredHolder<CriterionTrigger<?>, SimpleCriteria> USED_BLAST_MINING = RegistryHelper.triggerType("used_blast_mining", () -> new SimpleCriteria());
    public static final DeferredHolder<CriterionTrigger<?>, SimpleCriteria> RECEIVED_COMBUSTION_FROM_CREEPER = RegistryHelper.triggerType("received_combustion_from_creeper", () ->new SimpleCriteria());

    public static void init() {

    }

    /*
    @Override
    public void processField(CriterionTrigger<?> value, String name, Field field) {
        CriteriaTriggers.register(MythicMetals.MOD_ID + ":" + name, value);
    }

    @Override
    public Class<CriterionTrigger<?>> getTargetFieldType() {
        return AutoRegistryContainer.conform(CriterionTrigger.class);
    }
     */
}
