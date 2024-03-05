package net.chuck.chucksmod.datagen;

import net.chuck.chucksmod.block.ModBlocks;
import net.chuck.chucksmod.block.custom.LettuceCropBlock;
import net.chuck.chucksmod.block.custom.PineappleCropBlock;
import net.chuck.chucksmod.block.custom.TomatoCropBlock;
import net.chuck.chucksmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

/*
 *  Code inspired by or copied from
 *  Kaupenjoe
 *  Copyright (c) 2023
 *
 *  This code is licensed under MIT License
 *  Details can be found in the license file in the root folder of this project
 */
public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SANDSTONE_BRICKS);
        addDrop(ModBlocks.SANDSTONE_BRICK_STAIRS);
        addDrop(ModBlocks.SANDSTONE_BRICK_SLAB, slabDrops(ModBlocks.SANDSTONE_BRICK_SLAB));
        addDrop(ModBlocks.SANDSTONE_BRICKS);
        addDrop(ModBlocks.CRACKED_SANDSTONE_BRICKS);

        addDrop(ModBlocks.IRON_DUST_BLOCK);
        addDrop(ModBlocks.COPPER_DUST_BLOCK);
        addDrop(ModBlocks.GOLD_DUST_BLOCK);
        addDrop(ModBlocks.PRISMARINE_DUST_BLOCK);
        addDrop(ModBlocks.PRISMARINE_IRON_DUST_BLOCK);
        addDrop(ModBlocks.OBSIDIAN_SAND);

        addDrop(ModBlocks.HARDENED_GLASS);
        addDrop(ModBlocks.HARDENED_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_BLACK_GLASS);
        addDrop(ModBlocks.HARDENED_BLACK_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_BLUE_GLASS);
        addDrop(ModBlocks.HARDENED_BLUE_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_BROWN_GLASS);
        addDrop(ModBlocks.HARDENED_BROWN_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_CYAN_GLASS);
        addDrop(ModBlocks.HARDENED_CYAN_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_GRAY_GLASS);
        addDrop(ModBlocks.HARDENED_GRAY_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_GREEN_GLASS);
        addDrop(ModBlocks.HARDENED_GREEN_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_LIGHT_GRAY_GLASS);
        addDrop(ModBlocks.HARDENED_LIGHT_GRAY_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_LIGHT_BLUE_GLASS);
        addDrop(ModBlocks.HARDENED_LIGHT_BLUE_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_LIME_GLASS);
        addDrop(ModBlocks.HARDENED_LIME_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_MAGENTA_GLASS);
        addDrop(ModBlocks.HARDENED_MAGENTA_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_ORANGE_GLASS);
        addDrop(ModBlocks.HARDENED_ORANGE_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_PINK_GLASS);
        addDrop(ModBlocks.HARDENED_PINK_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_PURPLE_GLASS);
        addDrop(ModBlocks.HARDENED_PURPLE_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_RED_GLASS);
        addDrop(ModBlocks.HARDENED_RED_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_WHITE_GLASS);
        addDrop(ModBlocks.HARDENED_WHITE_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_YELLOW_GLASS);
        addDrop(ModBlocks.HARDENED_YELLOW_GLASS_PANE);
        addDrop(ModBlocks.HARDENED_TINTED_GLASS);

        addDrop(ModBlocks.TIN_DUST_BLOCK);
        addDrop(ModBlocks.TIN_BLOCK);
        addDrop(ModBlocks.RAW_TIN_BLOCK);
        addDrop(ModBlocks.TIN_BARS);
        addDrop(ModBlocks.TIN_ORE, oreDrops(ModBlocks.TIN_ORE, ModItems.RAW_TIN));
        addDrop(ModBlocks.DEEPSLATE_TIN_ORE, oreDrops(ModBlocks.DEEPSLATE_TIN_ORE, ModItems.RAW_TIN));
        addDrop(ModBlocks.TIN_WIRE);

        addDrop(ModBlocks.BRONZE_BLOCK);
        addDrop(ModBlocks.BRONZE_DUST_BLOCK);
        addDrop(ModBlocks.BRONZE_BARS);

        addDrop(ModBlocks.TITANIUM_DUST_BLOCK);
        addDrop(ModBlocks.TITANIUM_BLOCK);
        addDrop(ModBlocks.RAW_TITANIUM_BLOCK);
        addDrop(ModBlocks.TITANIUM_BARS);
        addDrop(ModBlocks.TITANIUM_ORE, oreDrops(ModBlocks.TITANIUM_ORE, ModItems.RAW_TITANIUM));
        addDrop(ModBlocks.DEEPSLATE_TITANIUM_ORE, oreDrops(ModBlocks.DEEPSLATE_TITANIUM_ORE, ModItems.RAW_TITANIUM));
        addDrop(ModBlocks.NETHER_TITANIUM_ORE, oreDrops(ModBlocks.NETHER_TITANIUM_ORE, ModItems.RAW_TITANIUM));
        addDrop(ModBlocks.END_STONE_TITANIUM_ORE, oreDrops(ModBlocks.END_STONE_TITANIUM_ORE, ModItems.RAW_TITANIUM));
        addDrop(ModBlocks.TITANIUM_MACHINE_BASE);
        addDrop(ModBlocks.TITANIUM_ENERGY_STORAGE);
        addDrop(ModBlocks.TITANIUM_POWERED_FURNACE);
        addDrop(ModBlocks.TITANIUM_POWERED_CRUSHER);
        addDrop(ModBlocks.TITANIUM_HEAT_GENERATOR);
        addDrop(ModBlocks.TITANIUM_QUARRY);
        addDrop(ModBlocks.TITANIUM_COPIER);
        addDrop(ModBlocks.TITANIUM_FLUID_TANK);
        addDrop(ModBlocks.TITANIUM_PUMP);
        addDrop(ModBlocks.TITANIUM_STEAM_GENERATOR);
        addDrop(ModBlocks.TITANIUM_LAVA_GENERATOR);

        addDrop(ModBlocks.SOUL_STONE);
        addDrop(ModBlocks.SOUL_STONE_STAIRS);
        addDrop(ModBlocks.SOUL_STONE_SLAB, slabDrops(ModBlocks.SOUL_STONE_SLAB));
        addDrop(ModBlocks.SOUL_STONE_BRICKS);
        addDrop(ModBlocks.SOUL_STONE_BRICK_STAIRS);
        addDrop(ModBlocks.SOUL_STONE_BRICK_SLAB, slabDrops(ModBlocks.SOUL_STONE_BRICK_SLAB));
        addDrop(ModBlocks.SOUL_STONE_BRICKS);
        addDrop(ModBlocks.CRACKED_SOUL_STONE_BRICKS);

        addDrop(ModBlocks.ECLOGITE);
        addDrop(ModBlocks.ECLOGITE_STAIRS);
        addDrop(ModBlocks.ECLOGITE_SLAB, slabDrops(ModBlocks.ECLOGITE_SLAB));
        addDrop(ModBlocks.ECLOGITE_WALL);
        addDrop(ModBlocks.ECLOGITE_BRICKS);
        addDrop(ModBlocks.ECLOGITE_BRICK_STAIRS);
        addDrop(ModBlocks.ECLOGITE_BRICK_SLAB, slabDrops(ModBlocks.ECLOGITE_BRICK_SLAB));
        addDrop(ModBlocks.ECLOGITE_BRICK_WALL);
        addDrop(ModBlocks.CRACKED_ECLOGITE_BRICKS);

        addDrop(ModBlocks.SERPENTINITE);
        addDrop(ModBlocks.SERPENTINITE_STAIRS);
        addDrop(ModBlocks.SERPENTINITE_SLAB, slabDrops(ModBlocks.SERPENTINITE_SLAB));
        addDrop(ModBlocks.SERPENTINITE_WALL);
        addDrop(ModBlocks.SERPENTINITE_BRICKS);
        addDrop(ModBlocks.SERPENTINITE_BRICK_STAIRS);
        addDrop(ModBlocks.SERPENTINITE_BRICK_SLAB, slabDrops(ModBlocks.SERPENTINITE_BRICK_SLAB));
        addDrop(ModBlocks.SERPENTINITE_BRICK_WALL);
        addDrop(ModBlocks.CRACKED_SERPENTINITE_BRICKS);
        addDrop(ModBlocks.POLISHED_SERPENTINITE);
        addDrop(ModBlocks.POLISHED_SERPENTINITE_STAIRS);
        addDrop(ModBlocks.POLISHED_SERPENTINITE_SLAB, slabDrops(ModBlocks.POLISHED_SERPENTINITE_SLAB));
        addDrop(ModBlocks.POLISHED_SERPENTINITE_WALL);

        addDrop(ModBlocks.SERPENTINITE_COAL_ORE, oreDrops(ModBlocks.SERPENTINITE_COAL_ORE, Items.COAL));
        addDrop(ModBlocks.SERPENTINITE_COPPER_ORE, copperOreDrops(ModBlocks.SERPENTINITE_COPPER_ORE));
        addDrop(ModBlocks.SERPENTINITE_DIAMOND_ORE, oreDrops(ModBlocks.SERPENTINITE_DIAMOND_ORE, Items.DIAMOND));
        addDrop(ModBlocks.SERPENTINITE_PACSARIUM_ORE, oreDrops(ModBlocks.SERPENTINITE_PACSARIUM_ORE, ModItems.RAW_PACSARIUM));
        addDrop(ModBlocks.SERPENTINITE_EMERALD_ORE, oreDrops(ModBlocks.SERPENTINITE_EMERALD_ORE, Items.EMERALD));
        addDrop(ModBlocks.SERPENTINITE_TRIAFIUM_ORE, oreDrops(ModBlocks.SERPENTINITE_TRIAFIUM_ORE, ModItems.RAW_TRIAFIUM));
        addDrop(ModBlocks.SERPENTINITE_GOLD_ORE, oreDrops(ModBlocks.SERPENTINITE_GOLD_ORE, Items.RAW_GOLD));
        addDrop(ModBlocks.SERPENTINITE_IRON_ORE, oreDrops(ModBlocks.SERPENTINITE_IRON_ORE, Items.RAW_IRON));
        addDrop(ModBlocks.SERPENTINITE_LAPIS_ORE, lapisOreDrops(ModBlocks.SERPENTINITE_LAPIS_ORE));
        addDrop(ModBlocks.SERPENTINITE_REDSTONE_ORE, redstoneOreDrops(ModBlocks.SERPENTINITE_REDSTONE_ORE));
        addDrop(ModBlocks.SERPENTINITE_TIN_ORE, oreDrops(ModBlocks.SERPENTINITE_TIN_ORE, ModItems.RAW_TIN));
        addDrop(ModBlocks.SERPENTINITE_TITANIUM_ORE, oreDrops(ModBlocks.SERPENTINITE_TITANIUM_ORE, ModItems.RAW_TITANIUM));

        addDrop(ModBlocks.TRIAFIA_SAPLING);
        addDrop(ModBlocks.TRIAFIA_LOG);
        addDrop(ModBlocks.TRIAFIA_WOOD);
        addDrop(ModBlocks.STRIPPED_TRIAFIA_LOG);
        addDrop(ModBlocks.STRIPPED_TRIAFIA_WOOD);
        addDrop(ModBlocks.TRIAFIA_LEAVES, leavesDrops(ModBlocks.TRIAFIA_LEAVES, ModBlocks.TRIAFIA_SAPLING,
                0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f));
        addDrop(ModBlocks.TRIAFIA_BUTTON);
        addDrop(ModBlocks.TRIAFIA_DOOR, doorDrops(ModBlocks.TRIAFIA_DOOR));
        addDrop(ModBlocks.TRIAFIA_TRAPDOOR);
        addDrop(ModBlocks.TRIAFIA_FENCE);
        addDrop(ModBlocks.TRIAFIA_FENCE_GATE);
        addDrop(ModBlocks.TRIAFIA_PRESSURE_PLATE);
        addDrop(ModBlocks.TRIAFIA_STAIRS);
        addDrop(ModBlocks.TRIAFIA_SLAB, slabDrops(ModBlocks.TRIAFIA_SLAB));
        addDrop(ModBlocks.TRIAFIA_PLANT, LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS)
                        .with(ItemEntry.builder(ModBlocks.TRIAFIA_PLANT))
                ).pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                        .with(ItemEntry.builder(ModItems.LETTUCE_SEEDS))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))
                ).pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))
                ).pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                        .with(ItemEntry.builder(ModItems.PINEAPPLE_SEEDS))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))
                ));
        
        addDrop(ModBlocks.RAW_TRIAFIUM_BLOCK);
        addDrop(ModBlocks.TRIAFIUM_BLOCK);
        addDrop(ModBlocks.TRIAFIUM_BARS);
        addDrop(ModBlocks.NETHER_TRIAFIUM_ORE, oreDrops(ModBlocks.NETHER_TRIAFIUM_ORE, ModItems.RAW_TRIAFIUM));
        addDrop(ModBlocks.END_STONE_TRIAFIUM_ORE, oreDrops(ModBlocks.END_STONE_TRIAFIUM_ORE, ModItems.RAW_TRIAFIUM));
        addDrop(ModBlocks.DEEPSLATE_TRIAFIUM_ORE, oreDrops(ModBlocks.DEEPSLATE_TRIAFIUM_ORE, ModItems.RAW_TRIAFIUM));
        addDrop(ModBlocks.TRIAFIUM_PORTAL_BLOCK);
        addDrop(ModBlocks.TRIAFIUM_PORTAL_BUILDER);

        addDrop(ModBlocks.RAW_PACSARIUM_BLOCK);
        addDrop(ModBlocks.PACSARIUM_BLOCK);
        addDrop(ModBlocks.PACSARIUM_BARS);
        addDrop(ModBlocks.END_STONE_PACSARIUM_ORE, oreDrops(ModBlocks.END_STONE_PACSARIUM_ORE,
                ModItems.RAW_PACSARIUM));
        addDrop(ModBlocks.DEEPSLATE_PACSARIUM_ORE, oreDrops(ModBlocks.DEEPSLATE_PACSARIUM_ORE,
                ModItems.RAW_PACSARIUM));

        addDrop(ModBlocks.EUCALYPTUS_SAPLING);
        addDrop(ModBlocks.EUCALYPTUS_LOG);
        addDrop(ModBlocks.EUCALYPTUS_WOOD);
        addDrop(ModBlocks.STRIPPED_EUCALYPTUS_LOG);
        addDrop(ModBlocks.STRIPPED_EUCALYPTUS_WOOD);
        addDrop(ModBlocks.EUCALYPTUS_LEAVES, leavesDrops(ModBlocks.EUCALYPTUS_LEAVES, ModBlocks.EUCALYPTUS_SAPLING,
                0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f));
        addDrop(ModBlocks.EUCALYPTUS_BUTTON);
        addDrop(ModBlocks.EUCALYPTUS_DOOR, doorDrops(ModBlocks.EUCALYPTUS_DOOR));
        addDrop(ModBlocks.EUCALYPTUS_TRAPDOOR);
        addDrop(ModBlocks.EUCALYPTUS_FENCE);
        addDrop(ModBlocks.EUCALYPTUS_FENCE_GATE);
        addDrop(ModBlocks.EUCALYPTUS_PRESSURE_PLATE);
        addDrop(ModBlocks.EUCALYPTUS_STAIRS);
        addDrop(ModBlocks.EUCALYPTUS_SLAB, slabDrops(ModBlocks.EUCALYPTUS_SLAB));

        addDrop(ModBlocks.PACSARIA_SAPLING);
        addDrop(ModBlocks.PACSARIA_LOG);
        addDrop(ModBlocks.PACSARIA_WOOD);
        addDrop(ModBlocks.STRIPPED_PACSARIA_LOG);
        addDrop(ModBlocks.STRIPPED_PACSARIA_WOOD);
        addDrop(ModBlocks.PACSARIA_LEAVES, leavesDrops(ModBlocks.PACSARIA_LEAVES, ModBlocks.PACSARIA_SAPLING,
                0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f));
        addDrop(ModBlocks.PACSARIA_BUTTON);
        addDrop(ModBlocks.PACSARIA_DOOR, doorDrops(ModBlocks.PACSARIA_DOOR));
        addDrop(ModBlocks.PACSARIA_TRAPDOOR);
        addDrop(ModBlocks.PACSARIA_FENCE);
        addDrop(ModBlocks.PACSARIA_FENCE_GATE);
        addDrop(ModBlocks.PACSARIA_PRESSURE_PLATE);
        addDrop(ModBlocks.PACSARIA_STAIRS);
        addDrop(ModBlocks.PACSARIA_SLAB, slabDrops(ModBlocks.PACSARIA_SLAB));
        addDrop(ModBlocks.PACSARIA_PLANT, grassDrops(ModBlocks.PACSARIA_PLANT));
        addDrop(ModBlocks.PACSARIA_LIGHT_PLANT);

        addDrop(ModBlocks.IRON_MACHINE_BASE);
        addDrop(ModBlocks.CRUSHER);
        addDrop(ModBlocks.IRON_POWERED_CRUSHER);
        addDrop(ModBlocks.IRON_POWERED_FURNACE);
        addDrop(ModBlocks.IRON_HEAT_GENERATOR);
        addDrop(ModBlocks.IRON_QUARRY);
        addDrop(ModBlocks.IRON_ENERGY_STORAGE);
        addDrop(ModBlocks.IRON_FLUID_TANK);
        addDrop(ModBlocks.IRON_PUMP);
        addDrop(ModBlocks.IRON_FLUID_PIPE);
        addDrop(ModBlocks.IRON_STEAM_GENERATOR);

        addDrop(ModBlocks.COPPER_WIRE);
        addDrop(ModBlocks.GOLD_WIRE);

        BlockStatePropertyLootCondition.Builder tomatoCropBuilder = BlockStatePropertyLootCondition.builder
                        (ModBlocks.TOMATO_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(TomatoCropBlock.AGE, TomatoCropBlock.MAX_AGE));
        addDrop(ModBlocks.TOMATO_CROP, cropDrops(ModBlocks.TOMATO_CROP, ModItems.TOMATO, ModItems.TOMATO_SEEDS,
                tomatoCropBuilder));

        BlockStatePropertyLootCondition.Builder lettuceCropBuilder = BlockStatePropertyLootCondition.builder
                        (ModBlocks.LETTUCE_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(LettuceCropBlock.AGE, LettuceCropBlock.MAX_AGE));
        addDrop(ModBlocks.LETTUCE_CROP, cropDrops(ModBlocks.LETTUCE_CROP, ModItems.LETTUCE, ModItems.LETTUCE_SEEDS,
                lettuceCropBuilder));

        BlockStatePropertyLootCondition.Builder pineappleCropBuilder = BlockStatePropertyLootCondition.builder
                        (ModBlocks.PINEAPPLE_CROP).properties(StatePredicate.Builder.create()
                .exactMatch(PineappleCropBlock.AGE, PineappleCropBlock.MAX_AGE));
        addDrop(ModBlocks.PINEAPPLE_CROP, cropDrops(ModBlocks.PINEAPPLE_CROP, ModItems.PINEAPPLE, ModItems.PINEAPPLE_SEEDS,
                pineappleCropBuilder));
    }
}
