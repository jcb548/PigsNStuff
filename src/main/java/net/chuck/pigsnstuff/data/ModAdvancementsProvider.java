package net.chuck.pigsnstuff.data;

import net.chuck.pigsnstuff.PigsNStuff;
import net.chuck.pigsnstuff.block.ModBlocks;
import net.chuck.pigsnstuff.item.ModItemTags;
import net.chuck.pigsnstuff.item.ModItems;
import net.chuck.pigsnstuff.world.gen.ModStructureKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureKeys;

import java.util.function.Consumer;

public class ModAdvancementsProvider extends FabricAdvancementProvider {
    public ModAdvancementsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        overworldAdvancements(consumer);
        netherAdvancements(consumer);
    }
    private void overworldAdvancements(Consumer<Advancement> consumer){
        Advancement root = Advancement.Builder.create()
                .display(Items.OAK_LOG, // The display icon
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.root.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.root.desc"), // The description
                        new Identifier(PigsNStuff.MOD_ID, "textures/block/eucalyptus_planks.png"), // Background image used
                        AdvancementFrame.TASK, true, true, false
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("got_logs", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create()
                        .tag(ItemTags.LOGS).build()))
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/root");

        Advancement craftingTable = Advancement.Builder.create()
                .display(Items.CRAFTING_TABLE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.crafting_table.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.crafting_table.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("crafting_table", InventoryChangedCriterion.Conditions.items(Items.CRAFTING_TABLE))
                .parent(root)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/crafting_table");

        Advancement woodPickaxe = Advancement.Builder.create()
                .display(Items.WOODEN_PICKAXE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.wooden_pickaxe.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.wooden_pickaxe.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_wooden_pick", InventoryChangedCriterion.Conditions.items(Items.WOODEN_PICKAXE))
                .parent(craftingTable)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/wooden_pickaxe");

        Advancement cobble = Advancement.Builder.create()
                .display(Items.COBBLESTONE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.cobble.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.cobble.desc"),
                        null, AdvancementFrame.GOAL, true, true, false)
                .criterion("get_cobble", InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
                .parent(woodPickaxe)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/cobble");

        Advancement stoneTools = Advancement.Builder.create()
                .display(Items.STONE_AXE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.stone_tools.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.stone_tools.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_stone_tools", InventoryChangedCriterion.Conditions.items(Items.STONE_SWORD,
                        Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_SHOVEL, Items.STONE_HOE))
                .parent(cobble)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/stone_tools");

        Advancement furnace = Advancement.Builder.create()
                .display(Items.FURNACE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.furnace.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.furnace.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_furnace", InventoryChangedCriterion.Conditions.items(Items.FURNACE))
                .parent(cobble)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/furnace");

        Advancement coal = Advancement.Builder.create()
                .display(Items.COAL,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.coal.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.coal.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_coal", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create()
                        .tag(ItemTags.COALS).build()))
                .parent(furnace)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/coal");

        Advancement meat = Advancement.Builder.create()
                .display(Items.PORKCHOP,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.meat.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.meat.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_meat", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create()
                        .tag(ModItemTags.RAW_MEAT).build()))
                .parent(stoneTools)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/meat");

        Advancement cookedMeat = Advancement.Builder.create()
                .display(Items.COOKED_PORKCHOP,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.cooked_meat.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.cooked_meat.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("cook_meat", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create()
                        .tag(ModItemTags.COOKED_MEAT).build()))
                .parent(meat)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/cooked_meat");

        Advancement seeds = Advancement.Builder.create()
                .display(Items.WHEAT_SEEDS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.seeds.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.seeds.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_seeds", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create()
                        .tag(ModItemTags.SEEDS).build()))
                .parent(stoneTools)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/seeds");

        Advancement farmland = Advancement.Builder.create().display(Items.FARMLAND,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.farmland.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.farmland.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("create_farmland", ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create().block(BlockPredicate.Builder.create()
                                .blocks(Blocks.FARMLAND).build()),
                        ItemPredicate.Builder.create().tag(ItemTags.HOES)))
                .parent(seeds)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/farmland");

        Advancement plant_wheat = Advancement.Builder.create().display(Items.WHEAT_SEEDS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_seeds.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_seeds.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("plant_wheat", ItemCriterion.Conditions.createPlacedBlock(Blocks.WHEAT))
                .parent(farmland)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/plant_wheat");

        Advancement wheat = Advancement.Builder.create().display(Items.WHEAT,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.wheat.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.wheat.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_wheat", InventoryChangedCriterion.Conditions.items(Items.WHEAT))
                .parent(plant_wheat)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/wheat");

        Advancement bread = Advancement.Builder.create().display(Items.BREAD,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bread.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bread.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_bread", InventoryChangedCriterion.Conditions.items(Items.BREAD))
                .parent(wheat)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/bread");

        Advancement plant_beetroot = Advancement.Builder.create().display(Items.BEETROOT_SEEDS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_beetroot.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_beetroot.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("plant_beetroot", ItemCriterion.Conditions.createPlacedBlock(Blocks.BEETROOTS))
                .parent(farmland)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/plant_beetroot");

        Advancement beetroot = Advancement.Builder.create().display(Items.BEETROOT,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.beetroot.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.beetroot.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_beetroot", InventoryChangedCriterion.Conditions.items(Items.BEETROOT))
                .parent(plant_beetroot)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/beetroot");

        Advancement plant_tomato = Advancement.Builder.create().display(ModItems.TOMATO_SEEDS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_tomato.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_tomato.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("plant_tomato", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.TOMATO_CROP))
                .parent(farmland)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/plant_tomato");

        Advancement tomato = Advancement.Builder.create().display(ModItems.TOMATO,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.tomato.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.tomato.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_tomato", InventoryChangedCriterion.Conditions.items(ModItems.TOMATO))
                .parent(plant_tomato)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/tomato");

        Advancement plant_lettuce = Advancement.Builder.create().display(ModItems.LETTUCE_SEEDS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_lettuce.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.plant_lettuce.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("plant_lettuce", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.LETTUCE_CROP))
                .parent(farmland)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/plant_lettuce");

        Advancement lettuce = Advancement.Builder.create().display(ModItems.LETTUCE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.lettuce.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.lettuce.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_lettuce", InventoryChangedCriterion.Conditions.items(ModItems.LETTUCE))
                .parent(plant_lettuce)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/lettuce");

        Advancement raw_iron = Advancement.Builder.create().display(Items.RAW_IRON,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.raw_iron.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.raw_iron.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_raw_iron", InventoryChangedCriterion.Conditions.items(Items.RAW_IRON))
                .parent(stoneTools)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/raw_iron");

        Advancement iron = Advancement.Builder.create().display(Items.IRON_INGOT,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron.desc"),
                        null, AdvancementFrame.GOAL, true, true, false)
                .criterion("get_iron", InventoryChangedCriterion.Conditions.items(Items.IRON_INGOT))
                .parent(raw_iron)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/iron");

        Advancement ironTools = Advancement.Builder.create()
                .display(Items.IRON_PICKAXE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron_tools.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron_tools.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_iron_tools", InventoryChangedCriterion.Conditions.items(Items.IRON_SWORD,
                        Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SHOVEL, Items.IRON_HOE))
                .parent(iron)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/iron_tools");

        Advancement ironArmor = Advancement.Builder.create()
                .display(Items.IRON_CHESTPLATE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron_armor.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron_armor.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_iron_armor", InventoryChangedCriterion.Conditions.items(Items.IRON_HELMET,
                        Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS))
                .parent(iron)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/iron_armor");

        Advancement diamonds = Advancement.Builder.create()
                .display(Items.DIAMOND,
                        Text.translatable("advancements."+ PigsNStuff.MOD_ID + ".overworld.diamonds.title"),
                        Text.translatable("advancements."+ PigsNStuff.MOD_ID + ".overworld.diamonds.desc"),
                        null, AdvancementFrame.GOAL, true, true, false)
                .criterion("get_diamonds", InventoryChangedCriterion.Conditions.items(Items.DIAMOND))
                .parent(ironTools)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/diamonds");

        Advancement diamondTools = Advancement.Builder.create()
                .display(Items.DIAMOND_SWORD,
                        Text.translatable("advancements."+ PigsNStuff.MOD_ID + ".overworld.diamond_tools.title"),
                        Text.translatable("advancements."+ PigsNStuff.MOD_ID + ".overworld.diamond_tools.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("diamond_tools", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_SWORD,
                        Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE))
                .parent(diamonds)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/diamond_tools");

        Advancement diamondArmor = Advancement.Builder.create()
                .display(Items.DIAMOND_CHESTPLATE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.diamond_armor.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.diamond_armor.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_diamond_armor", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_HELMET,
                        Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS))
                .parent(diamonds)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/diamond_armor");

        Advancement redstone = Advancement.Builder.create()
                .display(Items.REDSTONE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.redstone.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.redstone.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_redstone", InventoryChangedCriterion.Conditions.items(Items.REDSTONE))
                .parent(ironTools)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/redstone");

        Advancement piston = Advancement.Builder.create()
                .display(Blocks.PISTON,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.piston.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.piston.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_piston", InventoryChangedCriterion.Conditions.items(Blocks.PISTON))
                .parent(redstone)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/piston");

        Advancement ironGear = Advancement.Builder.create()
                .display(ModItems.IRON_GEAR,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron_gear.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.iron_gear.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_iron_gear", InventoryChangedCriterion.Conditions.items(ModItems.IRON_GEAR))
                .parent(piston)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/iron_gear");

        Advancement crusher = Advancement.Builder.create()
                .display(ModBlocks.CRUSHER,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.crusher.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.crusher.desc"),
                        null, AdvancementFrame.GOAL, true, true, false)
                .criterion("get_piston", InventoryChangedCriterion.Conditions.items(ModBlocks.CRUSHER))
                .parent(ironGear)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/crusher");

        Advancement dust = Advancement.Builder.create()
                .display(ModItems.IRON_DUST,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.dust.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.dust.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_dust", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create()
                        .tag(ModItemTags.DUSTS).build()))
                .parent(crusher)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/dust");

        Advancement obsidian = Advancement.Builder.create()
                .display(Blocks.OBSIDIAN,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.obsidian.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.obsidian.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_obsidian", InventoryChangedCriterion.Conditions.items(Blocks.OBSIDIAN))
                .parent(diamondTools)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/obsidian");

        Advancement gravel = Advancement.Builder.create()
                .display(Blocks.GRAVEL,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.gravel.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.gravel.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_gravel", InventoryChangedCriterion.Conditions.items(Blocks.GRAVEL))
                .parent(stoneTools)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/gravel");

        Advancement flint = Advancement.Builder.create()
                .display(Items.FLINT,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.flint.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.flint.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_flint", InventoryChangedCriterion.Conditions.items(Items.FLINT))
                .parent(gravel)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/flint");

        Advancement sugarCane = Advancement.Builder.create()
                .display(Items.SUGAR_CANE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.sugar_cane.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.sugar_cane.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_sugar_cane", InventoryChangedCriterion.Conditions.items(Items.SUGAR_CANE))
                .parent(root)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/sugar_cane");

        Advancement paper = Advancement.Builder.create()
                .display(Items.PAPER,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.paper.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.paper.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_paper", InventoryChangedCriterion.Conditions.items(Items.PAPER))
                .parent(sugarCane)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/sugar_cane");

        Advancement leather = Advancement.Builder.create()
                .display(Items.LEATHER,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.leather.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.leather.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_leather", InventoryChangedCriterion.Conditions.items(Items.LEATHER))
                .parent(meat)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/leather");

        Advancement book = Advancement.Builder.create()
                .display(Items.BOOK,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.book.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.book.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_book", InventoryChangedCriterion.Conditions.items(Items.BOOK))
                .parent(leather)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/book");


        Advancement enchantmentTable = Advancement.Builder.create()
                .display(Blocks.ENCHANTING_TABLE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.enchanting_table.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.enchanting_table.desc"),
                        null, AdvancementFrame.CHALLENGE, true, true, false)
                .criterion("get_enchanting_table", InventoryChangedCriterion.Conditions.items
                        (Blocks.ENCHANTING_TABLE))
                .parent(obsidian)
                .rewards(AdvancementRewards.Builder.loot(new Identifier("minecraft",
                        "blocks/lapis_ore")).addLoot(new Identifier("minecraft",
                        "blocks/lapis_ore")).addLoot(new Identifier("minecraft",
                        "blocks/lapis_ore")))
                .rewards(AdvancementRewards.Builder.experience(100))
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/enchanting_table");

        Advancement lapis = Advancement.Builder.create()
                .display(Items.LAPIS_LAZULI,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.lapis.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.lapis.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_lapis", InventoryChangedCriterion.Conditions.items(Items.LAPIS_LAZULI))
                .parent(stoneTools)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/lapis");

        Advancement flintAndSteel = Advancement.Builder.create()
                .display(Items.FLINT_AND_STEEL,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.flint_and_steel.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.flint_and_steel.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_flint_and_steel", InventoryChangedCriterion.Conditions.items(Items.FLINT_AND_STEEL))
                .parent(iron)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/flint_and_steel");

        Advancement netherPortal = Advancement.Builder.create().display(Blocks.NETHERRACK,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.nether_portal.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.nether_portal.desc"),
                        null, AdvancementFrame.CHALLENGE, true, true, false)
                .criterion("light_nether_portal", ItemCriterion.Conditions.createPlacedBlock(Blocks.NETHER_PORTAL))
                .parent(obsidian)
                .rewards(AdvancementRewards.Builder.experience(100))
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/nether_portal");

        Advancement enchantItem = Advancement.Builder.create().display(Items.ENCHANTED_BOOK,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.enchant_item.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.enchant_item.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("enchant_item", EnchantedItemCriterion.Conditions.any())
                .parent(enchantmentTable)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/enchant_item");

        Advancement bookshelf = Advancement.Builder.create().display(Blocks.BOOKSHELF,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bookshelf.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bookshelf.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("enchant_item", InventoryChangedCriterion.Conditions.items(Blocks.BOOKSHELF))
                .parent(book)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/bookshelf");

        Advancement village = Advancement.Builder.create().display(Blocks.OAK_PLANKS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.village.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.village.desc"),
                null, AdvancementFrame.TASK, true, true, false)
                .criteriaMerger(CriterionMerger.OR)
                .criterion("desert_village",
                        TickCriterion.Conditions.createLocation(LocationPredicate.feature(StructureKeys.VILLAGE_DESERT)))
                .criterion("plains_village",
                        TickCriterion.Conditions.createLocation(LocationPredicate.feature(StructureKeys.VILLAGE_PLAINS)))
                .criterion("savanna_village",
                        TickCriterion.Conditions.createLocation(LocationPredicate.feature(StructureKeys.VILLAGE_SAVANNA)))
                .criterion("snowy_village",
                        TickCriterion.Conditions.createLocation(LocationPredicate.feature(StructureKeys.VILLAGE_SNOWY)))
                .criterion("taiga_village",
                        TickCriterion.Conditions.createLocation(LocationPredicate.feature(StructureKeys.VILLAGE_TAIGA)))
                .parent(root)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/village");

        Advancement trade = Advancement.Builder.create().display(Items.EMERALD,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.trade.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.trade.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("trade", VillagerTradeCriterion.Conditions.any())
                .parent(village)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/trade");

        Advancement modern_tower = Advancement.Builder.create().display(Blocks.WHITE_CONCRETE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.modern_tower.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.modern_tower.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("modern_tower",
                        TickCriterion.Conditions.createLocation(LocationPredicate.feature(ModStructureKeys.MODERN_TOWER)))
                .parent(root)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/modern_tower");

        Advancement spider = Advancement.Builder.create().display(Blocks.COBWEB,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.spider.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.spider.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("killed_spider",
                        OnKilledCriterion.Conditions.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.SPIDER)))
                .parent(stoneTools)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/spider");

        Advancement string = Advancement.Builder.create()
                .display(Items.STRING,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.string.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.string.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_string", InventoryChangedCriterion.Conditions.items(Items.STRING))
                .parent(spider)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/string");

        Advancement sticks = Advancement.Builder.create()
                .display(Items.STICK,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.sticks.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.sticks.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("sticks", InventoryChangedCriterion.Conditions.items(Items.STICK))
                .parent(root)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/sticks");

        Advancement bow = Advancement.Builder.create()
                .display(Items.BOW,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bow.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bow.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("bow", InventoryChangedCriterion.Conditions.items(Items.BOW))
                .parent(string)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/bow");

        Advancement fishingRod = Advancement.Builder.create()
                .display(Items.FISHING_ROD,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.fishing_rod.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.fishing_rod.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("bow", InventoryChangedCriterion.Conditions.items(Items.FISHING_ROD))
                .parent(string)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/fishing_rod");

        Advancement shears = Advancement.Builder.create()
                .display(Items.SHEARS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.shears.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.shears.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("shears", InventoryChangedCriterion.Conditions.items(Items.SHEARS))
                .parent(iron)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/shears");

        Advancement wool = Advancement.Builder.create()
                .display(Blocks.WHITE_WOOL,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.wool.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.wool.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("wool", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().tag(ItemTags.WOOL).build()))
                .parent(shears)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/wool");

        Advancement bed = Advancement.Builder.create()
                .display(Items.RED_BED,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bed.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bed.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("bed", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().tag(ItemTags.BEDS).build()))
                .parent(wool)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/bed");

        Advancement sapling = Advancement.Builder.create()
                .display(Items.OAK_SAPLING,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.sapling.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.sapling.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("sapling", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().tag(ItemTags.SAPLINGS).build()))
                .parent(root)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/sapling");

        Advancement replantSapling = Advancement.Builder.create()
                .display(Items.SPRUCE_SAPLING,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.replant_sapling.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.replant_sapling.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criteriaMerger(CriterionMerger.OR)
                .criterion("replant_oak", ItemCriterion.Conditions.createPlacedBlock(Blocks.OAK_SAPLING))
                .criterion("replant_birch", ItemCriterion.Conditions.createPlacedBlock(Blocks.BIRCH_SAPLING))
                .criterion("replant_spruce", ItemCriterion.Conditions.createPlacedBlock(Blocks.SPRUCE_SAPLING))
                .criterion("replant_jungle", ItemCriterion.Conditions.createPlacedBlock(Blocks.JUNGLE_SAPLING))
                .criterion("replant_dark_oak", ItemCriterion.Conditions.createPlacedBlock(Blocks.DARK_OAK_SAPLING))
                .criterion("replant_acacia", ItemCriterion.Conditions.createPlacedBlock(Blocks.ACACIA_SAPLING))
                .criterion("replant_cherry", ItemCriterion.Conditions.createPlacedBlock(Blocks.CHERRY_SAPLING))
                .criterion("replant_mangrove", ItemCriterion.Conditions.createPlacedBlock(Blocks.MANGROVE_PROPAGULE))
                .criterion("replant_eucalyptus", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.EUCALYPTUS_SAPLING))
                .criterion("replant_diritia", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.DIRITIA_SAPLING))
                .parent(sapling)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/replant_sapling");

        Advancement zombie = Advancement.Builder.create().display(Blocks.ZOMBIE_HEAD,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.zombie.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.zombie.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("killed_zombie",
                        OnKilledCriterion.Conditions.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.ZOMBIE)))
                .parent(stoneTools)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/zombie");

        Advancement skeleton = Advancement.Builder.create().display(Blocks.SKELETON_SKULL,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.skeleton.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.skeleton.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("killed_skeleton",
                        OnKilledCriterion.Conditions.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.SKELETON)))
                .parent(stoneTools)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/skeleton");

        Advancement bone = Advancement.Builder.create()
                .display(Items.BONE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bone.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bone.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("bone", InventoryChangedCriterion.Conditions.items(Items.BONE))
                .parent(skeleton)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/bone");

        Advancement boneMeal = Advancement.Builder.create()
                .display(Items.BONE_MEAL,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bone_meal.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bone_meal.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("bone_meal", InventoryChangedCriterion.Conditions.items(Items.BONE_MEAL))
                .parent(bone)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/bone_meal");

        Advancement arrow = Advancement.Builder.create()
                .display(Items.BONE,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.arrow.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.arrow.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("bone", InventoryChangedCriterion.Conditions.items(Items.BONE))
                .parent(skeleton)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/bone");

        Advancement creeper = Advancement.Builder.create().display(Blocks.CREEPER_HEAD,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.creeper.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.creeper.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("killed_creeper",
                        OnKilledCriterion.Conditions.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.CREEPER)))
                .parent(stoneTools)
                .build(consumer, PigsNStuff.MOD_ID + "/overworld/creeper");

        Advancement gunpowder = Advancement.Builder.create()
                .display(Items.GUNPOWDER,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.gunpowder.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.gunpowder.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("gunpowder", InventoryChangedCriterion.Conditions.items(Items.GUNPOWDER))
                .parent(creeper)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/gunpowder");

        Advancement anvil = Advancement.Builder.create()
                .display(Items.ANVIL,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.anvil.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.anvil.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("anvil", InventoryChangedCriterion.Conditions.items(Items.ANVIL))
                .parent(enchantmentTable)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/anvil");

        Advancement bucket = Advancement.Builder.create()
                .display(Items.WATER_BUCKET,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bucket.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.bucket.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("bucket", InventoryChangedCriterion.Conditions.items(Items.BUCKET))
                .parent(iron)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/bucket");

        Advancement waterBucket = Advancement.Builder.create()
                .display(Items.WATER_BUCKET,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.water_bucket.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".overworld.water_bucket.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("water_bucket", InventoryChangedCriterion.Conditions.items(Items.WATER_BUCKET))
                .parent(bucket)
                .build(consumer,PigsNStuff.MOD_ID + "/overworld/water_bucket");
    }
    private void netherAdvancements(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.create()
                .display(Items.NETHERRACK, // The display icon
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.root.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.root.desc"), // The description
                        new Identifier("minecraft", "textures/block/nether_bricks.png"), // Background image used
                        AdvancementFrame.TASK, true, true, false
                )
                .criterion("enter_nether", ChangedDimensionCriterion.Conditions.to(World.NETHER))
                .build(consumer, PigsNStuff.MOD_ID + "/nether/root");

        Advancement rawFabium = Advancement.Builder.create().display(ModItems.RAW_FABIUM,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.raw_fabium.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.raw_fabium.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("raw_fabium", InventoryChangedCriterion.Conditions.items(ModItems.RAW_FABIUM))
                .parent(root)
                .build(consumer, PigsNStuff.MOD_ID + "/nether/raw_fabium");

        Advancement ancientDebris = Advancement.Builder.create().display(Blocks.ANCIENT_DEBRIS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.ancient_debris.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.ancient_debris.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("ancient_debris", InventoryChangedCriterion.Conditions.items(Blocks.ANCIENT_DEBRIS))
                .parent(root)
                .build(consumer, PigsNStuff.MOD_ID + "/nether/ancient_debris");

        Advancement quartz = Advancement.Builder.create().display(Items.QUARTZ,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.quartz.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.quartz.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("quartz", InventoryChangedCriterion.Conditions.items(Items.QUARTZ))
                .parent(root)
                .build(consumer, PigsNStuff.MOD_ID + "/nether/quartz");

        Advancement enterFortress = Advancement.Builder.create().display(Blocks.NETHER_BRICKS,
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.enter_fortress.title"),
                        Text.translatable("advancements." + PigsNStuff.MOD_ID + ".nether.enter_fortress.desc"),
                        null, AdvancementFrame.TASK, true, true, false)
                .criterion("enter_fortress",
                        TickCriterion.Conditions.createLocation(LocationPredicate.feature(StructureKeys.FORTRESS)))
                .parent(root)
                .build(consumer, PigsNStuff.MOD_ID + "/nether/fortress");
    }
}
