package com.mythicmetals.misc;

import com.mojang.authlib.GameProfile;
import com.mythicmetals.item.tools.HammerBase;
import eu.pb4.common.protection.api.CommonProtection;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import org.jetbrains.annotations.Nullable;

public class BlockBreaker {

    public static boolean isProtected(Level world, BlockPos blockPos, GameProfile profile, @Nullable Player player) {
        return !CommonProtection.canBreakBlock(world, blockPos, profile, player);
    }

    public static boolean isProtected(Level world, BlockPos blockPos, Explosion explosion, GameProfile profile, @Nullable Player player) {
        return !CommonProtection.canExplodeBlock(world, blockPos, explosion, profile, player);
    }

    public static Iterable<BlockPos> findBlocks(UseOnContext context, int depth) {

        Iterable<BlockPos> iterator;

        var facing = context.getSide().getOpposite();
        var pos = context.getBlockPos();
        var pos2 = context.getBlockPos().offset(facing, depth);

        if (facing.equals(Direction.DOWN) || facing.equals(Direction.UP)) {
            iterator = BlockPos.iterate(
                pos.east().offset(Direction.NORTH),
                pos2.west().offset(Direction.SOUTH)
            );
        } else {
            iterator = BlockPos.iterate(
                pos.down().offset(facing.rotateCounterclockwise(Direction.Axis.Y)),
                pos2.up().offset(facing.rotateClockwise(Direction.Axis.Y))
            );
        }


        return iterator;
    }

    public static Iterable<BlockPos> findBlocks(Direction facing, BlockPos pos, int depth) {
        Iterable<BlockPos> iterator;

        var pos2 = pos.offset(facing, depth);

        if (facing.equals(Direction.DOWN) || facing.equals(Direction.UP)) {
            iterator = BlockPos.iterate(
                pos.east().offset(Direction.NORTH),
                pos2.west().offset(Direction.SOUTH)
            );
        } else {
            iterator = BlockPos.iterate(
                pos.down().offset(facing.rotateCounterclockwise(Direction.Axis.Y)),
                pos2.up().offset(facing.rotateClockwise(Direction.Axis.Y))
            );
        }

        return iterator;
    }

    public static double getReachDistance(Player playerEntity) {
        return playerEntity.getAttributeValue(Attributes.PLAYER_BLOCK_INTERACTION_RANGE);
    }

    public static void initHammerTime() {
        // Original Block Pos is always the center block of where the hammer hits
        PlayerBlockBreakEvents.BEFORE.register((world, player, originalBlockPos, state, blockEntity) -> {
            var stack = player.getMainHandStack();

            if (!(stack.getItem() instanceof HammerBase hammer)) {
                return true; // don't do this for non-hammers
            }
            if (!hammer.isCorrectForDrops(stack, state)) {
                return true; // don't break anything extra if you are not mining rocks or stones
            }
            if (isProtected(world, originalBlockPos, player.getGameProfile(), player)) {
                return false;
            }
            var reach = BlockBreaker.getReachDistance(player);

            BlockHitResult blockHitResult = (BlockHitResult) player.raycast(reach, 1, false);

            var facing = blockHitResult.getSide().getOpposite();
            var blocks = BlockBreaker.findBlocks(facing, originalBlockPos, hammer.getDepth());

            boolean hasMined = false;
            for (BlockPos pos : blocks) {
                // Ignore the center block, to prevent an edge case where the middle block is broken thrice
                if (pos.equals(originalBlockPos)) {
                    continue;
                }
                if (isProtected(world, pos, player.getGameProfile(), player)) continue;
                if (hammer.canBreak(stack, world, pos) && !player.isCreative()) {
                    // Call Block.onBreak here, to allow interactions when a player breaks blocks
                    // Note that the center block still calls onBreak twice
                    world.getBlockState(pos).getBlock().onBreak(world, pos, state, player);
                    BlockEntity breakEntity = world.getBlockState(pos).getBlock() instanceof EntityBlock ? world.getBlockEntity(pos) : null;
                    Block.dropStacks(world.getBlockState(pos), world, originalBlockPos, breakEntity, player, stack);
                    world.breakBlock(pos, false, player);
                    hasMined = true;
                } else if (player.isCreative()) {
                    world.breakBlock(pos, false, null);
                }
            }
            if (hasMined) stack.damage(2, player, EquipmentSlot.MAINHAND);

            return true;
        });
    }

    public static float calculateHardestDelta(BlockHitResult blockHitResult, Player player, HammerBase hammer) {
        // The hardest, and slowest, delta
        // This speed is how much progress you are making each tick (I think...)
        float hardestDelta = 1.0F;

        // Create an iterator around the blocks that are about to be broken
        var hammeredBlocks = BlockBreaker.findBlocks(
            blockHitResult.getSide().getOpposite(), blockHitResult.getBlockPos(), hammer.getDepth());

        for (BlockPos pos : hammeredBlocks) {
            var state = player.getWorld().getBlockState(pos);
            // Ignore any blocks that are not minable
            if (!state.isAir() && hammer.isCorrectForDrops(hammer.getDefaultStack(), state)) {
                // Set the current delta to the lowest value in the block iterator
                var delta = player.getBlockBreakingSpeed(state) / 30 / state.getHardness(player.getWorld(), pos);
                if (hardestDelta > delta) {
                    hardestDelta = delta;
                }
            }
        }

        return hardestDelta;
    }
}
