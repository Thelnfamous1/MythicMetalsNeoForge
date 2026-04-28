package com.mythicmetals.item;

import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.network.chat.Component;
import java.util.List;

public class TippedRuniteArrowItem extends RuniteArrowItem {

    public TippedRuniteArrowItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        var stack = new ItemStack(MythicTools.TIPPED_RUNITE_ARROW);
        stack.set(DataComponents.POTION_CONTENTS, new PotionContentsComponent(Potions.POISON));
        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        PotionContents potionContentsComponent = stack.get(DataComponents.POTION_CONTENTS);
        if (potionContentsComponent != null) {
            potionContentsComponent.buildTooltip(tooltip::add, 0.125F, context.getUpdateTickRate());
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return Potion.finishTranslationKey(
            stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.DEFAULT).potion(),
            this.getTranslationKey() + ".effect."
        );
    }
}
