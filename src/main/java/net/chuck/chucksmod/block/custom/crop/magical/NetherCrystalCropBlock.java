package net.chuck.chucksmod.block.custom.crop.magical;

import net.chuck.chucksmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class NetherCrystalCropBlock extends AbstractMagicalCropBlock {
    public NetherCrystalCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.NETHER_CRYSTAL_SEEDS;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.BASE_STONE_NETHER) || floor.isIn(BlockTags.SOUL_FIRE_BASE_BLOCKS);
    }
}
