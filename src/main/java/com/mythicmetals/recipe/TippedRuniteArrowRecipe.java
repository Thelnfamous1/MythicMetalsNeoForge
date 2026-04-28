package com.mythicmetals.recipe;

import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.Level;

public class TippedRuniteArrowRecipe extends CustomRecipe {
    public TippedRuniteArrowRecipe(CraftingBookCategory craftingRecipeCategory) {
        super(craftingRecipeCategory);
    }

    public boolean matches(CraftingInput input, Level world) {
        if (input.getWidth() == 3 && input.getHeight() == 3) {
            for (int i = 0; i < input.getWidth(); ++i) {
                for (int j = 0; j < input.getHeight(); ++j) {
                    ItemStack itemStack = input.getStackInSlot(i + j * input.getWidth());
                    if (itemStack.isEmpty()) {
                        return false;
                    }

                    if (i == 1 && j == 1) {
                        if (!itemStack.isOf(Items.LINGERING_POTION)) {
                            return false;
                        }
                    } else if (!itemStack.isOf(MythicTools.RUNITE_ARROW)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public ItemStack craft(CraftingInput input, HolderLookup.Provider wrapperLookup) {
        ItemStack itemStack = input.getStackInSlot(1 + input.getWidth());
        if (!itemStack.isOf(Items.LINGERING_POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack2 = new ItemStack(MythicTools.TIPPED_RUNITE_ARROW, 8);
            itemStack2.set(DataComponents.POTION_CONTENTS, itemStack.get(DataComponents.POTION_CONTENTS));
            return itemStack2;
        }
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MythicRecipeSerializers.TIPPED_RUNITE_ARROW_RECIPE;
    }
}
