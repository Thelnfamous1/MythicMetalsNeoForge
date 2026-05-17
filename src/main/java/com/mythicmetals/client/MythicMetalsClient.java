// TODO(Ravel): Failed to fully resolve file: null cannot be cast to non-null type com.intellij.psi.PsiClass
package com.mythicmetals.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.brigadier.CommandDispatcher;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.*;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.rendering.*;
import com.mythicmetals.compat.IsometricArmorStandExporter;
import com.mythicmetals.component.*;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.item.tools.*;
import com.mythicmetals.misc.*;
import com.mythicmetals.mixin.LevelRendererInvoker;
import io.wispforest.owo.ui.util.Delta;
//import net.fabricmc.api.ClientModInitializer;
//import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
//import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
//import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
//import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.*;
//import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT)
public class MythicMetalsClient /*implements ClientModInitializer*/ {
    private long lastTime;
    private float time;
    public static ItemDisplayContext mode;

    //@Override
    public void onInitializeClient() {
        //MythicModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        renderHammerOutline();
        registerArmorRenderer();
        registerModelPredicates();
        registerSwirlRenderer();

        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(player -> !CelestiumElytra.isWearing(player));

        /*
        EntityRendererRegistry.register(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE.get(), PalladiumMinecartRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE.get(), BanglumTntMinecartEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_ENTITY_TYPE.get(), BanglumTntEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE.get(), BanglumNukeEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE.get(), StarPlatinumArrowEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.RUNITE_ARROW_ENTITY_TYPE.get(), RuniteArrowEntityRenderer::new);
         */

        //BlockEntityRenderers.register(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK.get(), EnchantedMidasBlockEntityRenderer::new);

        //ColorProviderRegistry.ITEM.register(UsefulSingletonForColorUtil::potionColor, MythicTools.TIPPED_RUNITE_ARROW);

        CarmotShieldHudHandler.init();
        //ClientTickEvents.END_CLIENT_TICK.register(client -> CarmotShieldHudHandler.tick());

        /*
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.CARMOT_BELL_BLOCK.get(), RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.PALLADIUM_RAIL.get(), RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.AQUARIUM_GLASS.get(), RenderType.translucent());
         */
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.CARMOT_BELL_BLOCK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.PALLADIUM_RAIL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.AQUARIUM_GLASS.get(), RenderType.translucent());

        //BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), MythicBlocks.KYBER.getStorageBlock());
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.KYBER.getStorageBlock(), RenderType.translucent());

        if (ModList.get().isLoaded("isometric-renders")) {
            //ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            NeoForge.EVENT_BUS.addListener((RegisterClientCommandsEvent event) -> {
                CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
                IsometricArmorStandExporter.register(dispatcher);
            });
        }

        registerTooltipCallbacks();
    }

    @SubscribeEvent
    static void onClientTickPost(ClientTickEvent.Post event) {
        CarmotShieldHudHandler.tick();
    }

    @SubscribeEvent
    static void onRegisterColors(RegisterColorHandlersEvent.Item event){
        event.register(UsefulSingletonForColorUtil::potionColor, MythicTools.TIPPED_RUNITE_ARROW);
    }

    @SubscribeEvent
    static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE.get(), PalladiumMinecartRenderer::new);
        event.registerEntityRenderer(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE.get(), BanglumTntMinecartEntityRenderer::new);
        event.registerEntityRenderer(MythicEntities.BANGLUM_TNT_ENTITY_TYPE.get(), BanglumTntEntityRenderer::new);
        event.registerEntityRenderer(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE.get(), BanglumNukeEntityRenderer::new);
        event.registerEntityRenderer(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE.get(), StarPlatinumArrowEntityRenderer::new);
        event.registerEntityRenderer(MythicEntities.RUNITE_ARROW_ENTITY_TYPE.get(), RuniteArrowEntityRenderer::new);

        event.registerBlockEntityRenderer(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK.get(), EnchantedMidasBlockEntityRenderer::new);
    }

    @SubscribeEvent
    static void onRegisterModelLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        MythicModelHandler.init((modelLayerLocation, layerDefinition) -> event.registerLayerDefinition(modelLayerLocation, () -> layerDefinition));
    }

    @SubscribeEvent
    static void onAddRendererLayers(EntityRenderersEvent.AddLayers event) {
        EntityRendererProvider.Context context = event.getContext();
        EntityRenderer<? extends Player> renderer = event.getRenderer(EntityType.PLAYER);
        if(renderer instanceof PlayerRenderer playerRenderer){
            ((PlayerRenderer) renderer).addLayer(new PlayerEnergySwirlFeatureRenderer(
                    playerRenderer,
                    context.getModelSet()));
        }
    }

    @SuppressWarnings("unchecked")
    private void registerSwirlRenderer() {
        //LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
    }

    /**
     * Renders the outline of a {@link HammerBase hammer item.}
     */
    private void renderHammerOutline() {
        //WorldRenderEvents.BLOCK_OUTLINE.register((worldRenderContext, blockOutlineContext) -> {
        NeoForge.EVENT_BUS.addListener((RenderHighlightEvent.Block event) -> {
            Entity cameraEntity = event.getCamera().getEntity();
            Vec3 cameraPos = event.getCamera().getPosition();
            BlockHitResult hitResult = event.getTarget();
            BlockPos ctxBlockPos = hitResult.getBlockPos();
            Level level = Minecraft.getInstance().level;
            BlockState ctxBlockState = level.getBlockState(ctxBlockPos);
            PoseStack poseStack = event.getPoseStack();
            MultiBufferSource multiBufferSource = event.getMultiBufferSource();
            if (!/*blockOutlineContext.entity()*/cameraEntity.isAlwaysTicking()) /*return true*/ return;
            var player = (AbstractClientPlayer) /*blockOutlineContext.entity()*/cameraEntity;

            // Only render the outline if you are hovering over something the hammer can break
            var stack = player.getMainHandItem();
            if (stack.getItem() instanceof HammerBase hammer
                && !/*blockOutlineContext.blockState()*/ctxBlockState.isAir()
                && hammer.isCorrectToolForDrops(stack, /*blockOutlineContext.blockState()*/ctxBlockState)) {

                var reach = BlockBreaker.getReachDistance(player);
                BlockHitResult blockHitResult = (BlockHitResult) player.pick(reach, 1, false);

                var facing = blockHitResult.getDirection().getOpposite();
                var blocks = BlockBreaker.findBlocks(facing, /*blockOutlineContext.blockPos()*/ctxBlockPos, hammer.getDepth());
                var originalPos = /*blockOutlineContext.blockPos()*/ctxBlockPos;

                // Create VoxelShapes out of the block positions and put them in a list
                var voxels = new ArrayList<VoxelShape>();

                for (BlockPos blockPos : blocks) {
                    var blockState = player.level().getBlockState(blockPos);
                    if (!blockState.isAir() && hammer.isCorrectToolForDrops(stack, blockState)) {
                        voxels.add(blockState.getShape(
                                /*worldRenderContext.world()*/level,
                                blockPos,
                                CollisionContext.of(/*blockOutlineContext.entity()*/cameraEntity)
                            ).move(blockPos.getX() - originalPos.getX(),
                                blockPos.getY() - originalPos.getY(),
                                blockPos.getZ() - originalPos.getZ())
                        );
                    }
                }

                // Combine and render the full shape
                var outlineOptional = voxels.stream().reduce(Shapes::or);
                if (outlineOptional.isEmpty()) /*return true*/return;

                var outlineShape = outlineOptional.get();

                LevelRendererInvoker.mythicmetals$drawShapeOutline(
                    /*worldRenderContext.matrixStack()*/poseStack,
                    /*worldRenderContext.consumers()*/multiBufferSource.getBuffer(RenderType.lines()),
                    outlineShape,
                    originalPos.getX() - /*blockOutlineContext.cameraX()*/cameraPos.x,
                    originalPos.getY() - /*blockOutlineContext.cameraY()*/cameraPos.y,
                    originalPos.getZ() - /*blockOutlineContext.cameraZ()*/cameraPos.z,
                    0, 0, 0, 0.4F //RGBA
                );
                // Cancel the event to prevent the middle outline from rendering
                /*return false;*/event.setCanceled(true);
                return;
            }

            // Keep moving along if we reach this point
            //return true;
        });
    }

    @SubscribeEvent
    static void onRegisterClientItemExtensions(RegisterClientExtensionsEvent event) {
        Item[] armors = BuiltInRegistries.ITEM.stream()
                .filter(i -> i instanceof HallowedArmor
                        && BuiltInRegistries.ITEM.getKey(i).getNamespace().equals(MythicMetals.MOD_ID))
                .toArray(Item[]::new);
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                HallowedArmor armor = (HallowedArmor) itemStack.getItem();
                HumanoidModel model = armor.getArmorModel();
                original.copyPropertiesTo(model);
                return model;
            }
        }, armors);
    }

    private void registerArmorRenderer() {
        /*
        Item[] armors = BuiltInRegistries.ITEM.stream()
            .filter(i -> i instanceof HallowedArmor
                && BuiltInRegistries.ITEM.getKey(i).getNamespace().equals(MythicMetals.MOD_ID))
            .toArray(Item[]::new);
        ArmorRenderer renderer = (matrices, vertexConsumer, stack, entity, slot, light, original) -> {


            var texture = armor.getArmorTexture(stack, slot);
            original.copyPropertiesTo(model);
            ArmorRenderer.renderPart(matrices, vertexConsumer, light, stack, model, texture);

            // Armor trim time
            if (!stack.is(MythicArmor.HALLOWED.getHelmet())) {
                var trimComponent = stack.get(DataComponents.TRIM);
                if (trimComponent != null) {
                    var atlas = Minecraft.getInstance().getTextureAtlas(Sheets.ARMOR_TRIMS_SHEET);
                    TextureAtlasSprite sprite = atlas.apply(slot == EquipmentSlot.LEGS ? trimComponent.innerTexture(armor.getMaterial()) : trimComponent.outerTexture(armor.getMaterial()));
                    VertexConsumer trimVertexConsumer = sprite.wrap(
                        ItemRenderer.getFoilBufferDirect(vertexConsumer, Sheets.armorTrimsSheet(trimComponent.pattern().value().decal()), true, stack.hasFoil())
                    );
                    model.renderToBuffer(matrices, trimVertexConsumer, light, OverlayTexture.NO_OVERLAY);
                }
            }
        };
        ArmorRenderer.register(renderer, armors);
         */
    }

    private void registerModelPredicates() {
        ItemProperties.register(
            MythicTools.LEGENDARY_BANGLUM.getPickaxe(), RegistryHelper.id("is_primed"),
            (stack, world, entity, seed) -> BanglumPick.isCoolingDown(entity, stack) ? 0 : 1
        );

        ItemProperties.register(
            MythicTools.LEGENDARY_BANGLUM.getShovel(), RegistryHelper.id("is_primed"),
            (stack, world, entity, seed) -> BanglumShovel.isCoolingDown(entity, stack) ? 0 : 1
        );

        ItemProperties.register(
            MythicTools.MYTHRIL_DRILL.get(), RegistryHelper.id("is_active"),
            (stack, world, entity, seed) -> stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel() ? 0 : 1
        );

        registerMidasPredicates(MythicTools.MIDAS_GOLD_SWORD.get());
        registerMidasPredicates(MythicTools.GILDED_MIDAS_GOLD_SWORD.get());
        registerMidasPredicates(MythicTools.ROYAL_MIDAS_GOLD_SWORD.get());

        ItemProperties.registerGeneric(RegistryHelper.id("in_world"), (itemStack, world, livingEntity, i) -> {
            if (mode == null) {
                return 1.0f;
            }

            return mode.equals(ItemDisplayContext.GUI) ? 0.0F : 1.0f;
        });

        ItemProperties.register(MythicTools.STORMYX_SHIELD.get(), RegistryHelper.id("blocking"), new ShieldUsePredicate());

        ItemProperties.registerGeneric(RegistryHelper.id("funny_day"), (stack, world, entity, seed) ->
            (StringUtilsAtHome.isFunnyDay()) ? 1 : 0);

        ItemProperties.register(MythicTools.PLATINUM_WATCH.get(), RegistryHelper.id("time"), (stack, world, entity, seed) -> {
            if (entity == null || entity.level() == null) {
                return 0.0F;
            }
            return this.getTime(entity.level());
        });

    }

    public void registerTooltipCallbacks() {
        //ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
        NeoForge.EVENT_BUS.addListener((ItemTooltipEvent event) -> {
            ItemStack stack = event.getItemStack();
            List<Component> lines = event.getToolTip();
            TooltipFlag type = event.getFlags();
            int index = 1;

            if (stack.is(MythicTags.BONUS_FORTUNE)) {
                lines.add(index, Component.translatable("abilities.mythicmetals.bonus_fortune").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (stack.is(MythicTags.BONUS_LOOTING)) {
                lines.add(index, Component.translatable("abilities.mythicmetals.bonus_looting").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (lines.size() > 2) {
                index += stack.getEnchantments().size();
            }

            if (stack.has(MythicDataComponents.PROMETHEUM)) {
                var component = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
                if (type.isAdvanced()) {
                    lines.add(index, Component.translatable("tooltip.prometheum.repaired", component.durabilityRepaired())
                        .withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb())
                    );
                }

                lines.add(index, Component.translatable("tooltip.prometheum.regrowth").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                if (component.isOvergrown()) {
                    lines.add(index, Component.translatable("tooltip.prometheum.overgrown").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
                if (EnchantmentHelper.has(stack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
                    lines.add(index, Component.translatable("tooltip.prometheum.engrained").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
            }
        });


    }

    private float getTime(Level world) {
        if (world.getDayTime() != this.lastTime) {
            this.lastTime = world.getDayTime();
            this.time += Delta.compute(
                this.time,
                (world.getDayTime()) / 24000.0f,
                Minecraft.getInstance().getTimer().getGameTimeDeltaTicks() / 2.0f
            );
        }

        return this.time;
    }

    public void registerMidasPredicates(Item item) {
        ItemProperties.register(item, RegistryHelper.id("midas_gold_count"),
            (stack, world, entity, seed) -> {
                int goldCount = stack.getOrDefault(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0)).goldFolded();
                return MidasGoldSword.countGold(goldCount);
            });
    }

}
