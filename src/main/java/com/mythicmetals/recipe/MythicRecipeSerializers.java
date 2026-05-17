package com.mythicmetals.recipe;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MythicRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MythicMetals.MOD_ID);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<TippedRuniteArrowRecipe>> TIPPED_RUNITE_ARROW_RECIPE = RegistryHelper.recipeSerializer("runite_tipped_arrow_recipe", () -> new SimpleCraftingRecipeSerializer<>(TippedRuniteArrowRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MidasFoldingRecipe>> MIDAS_FOLDING_RECIPE = RegistryHelper.recipeSerializer("fold_midas_sword", MidasFoldingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<TidesingerCoralRecipe>> TIDESINGER_CORAL_RECIPE = RegistryHelper.recipeSerializer("tidesinger_smithing_transform", () -> new TidesingerCoralRecipe.Serializer(TidesingerCoralRecipe.Serializer.ENDEC));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<UpgradeSmithingRecipe>> UPGRADE_SMITHING_RECIPE_SERIALIZER = RegistryHelper.recipeSerializer("upgrading", () -> new UpgradeSmithingRecipe.Serializer(UpgradeSmithingRecipe.Serializer.ENDEC));


    public static void init() {
        /*
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("runite_tipped_arrow_recipe"), TIPPED_RUNITE_ARROW_RECIPE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_sword"), MIDAS_FOLDING_RECIPE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("tidesinger_smithing_transform"), TIDESINGER_CORAL_RECIPE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("upgrading"), UPGRADE_SMITHING_RECIPE_SERIALIZER);
         */
    }
}
