package net.chuck.pigsnstuff;

import net.chuck.pigsnstuff.block.ModBlocks;
import net.chuck.pigsnstuff.client.ArmorHudOverlay;
import net.chuck.pigsnstuff.entity.ModEntities;
import net.chuck.pigsnstuff.entity.custom.client.FrankFireballModel;
import net.chuck.pigsnstuff.entity.custom.client.FrankFireballRenderer;
import net.chuck.pigsnstuff.entity.custom.client.FrankRenderer;
import net.chuck.pigsnstuff.entity.custom.client.WyattRenderer;
import net.chuck.pigsnstuff.networking.ModMessages;
import net.chuck.pigsnstuff.screen.CrusherScreen;
import net.chuck.pigsnstuff.screen.GeneratorScreen;
import net.chuck.pigsnstuff.screen.ModScreenHandlers;
import net.chuck.pigsnstuff.screen.PoweredCrusherScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

/*
 *  Code inspired by or copied from
 *  Kaupenjoe
 *  Copyright (c) 2023
 *
 *  This code is licensed under MIT License
 *  Details can be found in the license file in the root folder of this project
 */
public class PigsNStuffClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EUCALYPTUS_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SLATED_GRASS, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos),
                ModBlocks.SLATED_GRASS);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIRITIA_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIRITIA_LIGHT_PLANT, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos),
                ModBlocks.DIRITIA_PLANT);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0xFE8738,
                ModBlocks.DIRITIA_LIGHT_PLANT);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIRITIA_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIRITIA_LEAVES, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos),
                ModBlocks.DIRITIA_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIRITIA_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIRITIA_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLD_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BRONZE_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TIN_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRISMARINE_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIRITONIUM_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FABIUM_BARS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_TINTED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_BLACK_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_BLACK_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_BLUE_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_BLUE_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_BROWN_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_BROWN_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_CYAN_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_CYAN_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_GRAY_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_GRAY_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_GREEN_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_GREEN_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_LIGHT_BLUE_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_LIGHT_BLUE_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_LIGHT_GRAY_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_LIGHT_GRAY_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_LIME_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_LIME_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_MAGENTA_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_MAGENTA_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_ORANGE_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_ORANGE_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_PINK_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_PINK_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_PURPLE_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_PURPLE_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_RED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_RED_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_WHITE_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_WHITE_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_YELLOW_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HARDENED_YELLOW_GLASS_PANE, RenderLayer.getTranslucent());

        HandledScreens.register(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, CrusherScreen::new);
        HandledScreens.register(ModScreenHandlers.POWERED_CRUSHER_SCREEN_HANDLER, PoweredCrusherScreen::new);
        HandledScreens.register(ModScreenHandlers.GENERATOR_SCREEN_HANDLER, GeneratorScreen::new);

        HudRenderCallback.EVENT.register(new ArmorHudOverlay());
        EntityRendererRegistry.register(ModEntities.WYATT, WyattRenderer::new);
        EntityRendererRegistry.register(ModEntities.FRANK_BOSS, FrankRenderer::new);
        EntityRendererRegistry.register(ModEntities.FRANK_FIREBALL, FrankFireballRenderer::new);
    }
}
