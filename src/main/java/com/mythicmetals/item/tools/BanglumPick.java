package com.mythicmetals.item.tools;

import com.mythicmetals.misc.BlockBreaker;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.registry.RegisterCriteria;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.item.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public class BanglumPick extends PickaxeItem {

    public BanglumPick(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    /**
     * Method for the legendary banglum pickaxe breaking ability.
     * When the tool is used on a block, it breaks a bunch of blocks in a set radius.
     */
    @Override
    public InteractionResult useOnBlock(ItemUsageContext context) {
        boolean shouldPass = false;
        var world = context.getWorld();
        var player = context.getPlayer();

        if (player != null && !isCoolingDown(player, context.getStack()) && !world.isClient()) {

            var iterator = BlockBreaker.findBlocks(context, 5);
            for (BlockPos blockPos : iterator) {
                if (BlockBreaker.isProtected(world, blockPos, player.getGameProfile(), player)) {
                    continue;
                }
                if (isCorrectForDrops(context.getStack(), world.getBlockState(blockPos))) {
                    WorldOps.breakBlockWithItem(world, blockPos, context.getStack(), player);
                    context.getStack().damage(2, player, EquipmentSlot.MAINHAND);
                    shouldPass = true;
                }
            }

        }

        if (shouldPass) {
            var pos = context.getBlockPos();
            var facing = context.getSide().getOpposite();
            var pos2 = context.getBlockPos().offset(facing, 5);

            MythicParticleSystem.EXPLOSION_TRAIL.spawn(world, Vec3.of(pos), Vec3.of(pos2));
            WorldOps.playSound(world, pos, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundSource.PLAYERS);

            RegisterCriteria.USED_BLAST_MINING.trigger((ServerPlayer) player);
            player.getItemCooldownManager().set(this, 100);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    public static boolean isCoolingDown(LivingEntity entity, ItemStack stack) {
        if (entity != null && entity.isPlayer()) {
            return ((Player) entity).getItemCooldownManager().isCoolingDown(stack.getItem());
        }
        return false;
    }
}
