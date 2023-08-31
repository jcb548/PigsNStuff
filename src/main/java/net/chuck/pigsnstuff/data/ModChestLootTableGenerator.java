package net.chuck.pigsnstuff.data;

import net.chuck.pigsnstuff.PigsNStuff;
import net.chuck.pigsnstuff.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class ModChestLootTableGenerator extends SimpleFabricLootTableProvider {
    public static final Identifier DIRITIA_HOUSE = new Identifier(PigsNStuff.MOD_ID, "chests/diritia_house");
    public ModChestLootTableGenerator(FabricDataOutput output) {
        super(output, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        /*exporter.accept(DIRITIA_HOUSE, LootTable.builder()
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(1.0f, 3.0f))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.SADDLE).weight(20))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.GOLDEN_APPLE).weight(15))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(2))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(2))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.MUSIC_DISC_13).weight(15))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.MUSIC_DISC_CAT).weight(15))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.NAME_TAG).weight(20))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(10))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(15))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(5))
                    .with((LootPoolEntry.Builder<?>)((Object) ((LeafEntry.Builder)ItemEntry.builder(Items.BOOK).weight(10))
                        .apply(EnchantRandomlyLootFunction.builder()))))
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(1.0f, 4.0f))
                    .with((LootPoolEntry.Builder<?>)((Object) ((LeafEntry.Builder)ItemEntry.builder(Items.IRON_INGOT).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.GOLD_INGOT).weight(5))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.BREAD).weight(20))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.WHEAT).weight(20))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))))
                    .with((LootPoolEntry.Builder<?>)ItemEntry.builder(Items.BUCKET).weight(10))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.REDSTONE).weight(15))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.COAL).weight(15))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.MELON_SEEDS).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.PUMPKIN_SEEDS).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.BEETROOT_SEEDS).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(3.0f))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.BONE).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.GUNPOWDER).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.ROTTEN_FLESH).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))))
                    .with((LootPoolEntry.Builder<?>)((Object)((LeafEntry.Builder)ItemEntry.builder(Items.STRING).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))))));*/
        exporter.accept(DIRITIA_HOUSE, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(15))
                    .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(5))
                    .with(ItemEntry.builder(ModItems.DIRITONIUM_INGOT).weight(10))
                    .with(ItemEntry.builder(Items.NAME_TAG).weight(20))
                    .with(ItemEntry.builder(Items.BOOK).weight(30))
                        .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0f, 39.0f))))
                .pool(LootPool.builder().rolls(UniformLootNumberProvider.create(1.0f, 4.0f))
                    .with(ItemEntry.builder(Items.REDSTONE).weight(15))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                    .with(ItemEntry.builder(Items.POTION).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))
                        .apply(SetPotionLootFunction.builder(Potions.STRONG_REGENERATION))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(3.0f))
                    .with(ItemEntry.builder(Items.IRON_INGOT).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))
                    .with(ItemEntry.builder(Items.GOLD_INGOT).weight(5))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                    .with(ItemEntry.builder(Items.COPPER_INGOT).weight(15))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))
                    .with(ItemEntry.builder(ModItems.PRISMARINE_INGOT).weight(5))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))
                    .with(ItemEntry.builder(ModItems.BRONZE_INGOT).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))
                    .with(ItemEntry.builder(ModItems.TIN_INGOT).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                    .with(ItemEntry.builder(ModItems.CHEESEBURGER).weight(20))
                    .with(ItemEntry.builder(Items.QUARTZ).weight(10))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 12.0f)))
                    .with(ItemEntry.builder(Items.COAL).weight(15))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))
                    .with(ItemEntry.builder(ModItems.RAW_DIRITONIUM).weight(10))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))
                    .with(ItemEntry.builder(Items.BONE).weight(10))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))
                    .with(ItemEntry.builder(Items.BLAZE_POWDER).weight(10))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f)))));

    }
}
