package net.chuck.chucksmod.block.entity.generator;
import net.chuck.chucksmod.block.entity.ModBlockEntities;
import net.chuck.chucksmod.block.entity.tiers.TitaniumTier;
import net.chuck.chucksmod.screen.generator.IronHeatGeneratorScreenHandler;
import net.chuck.chucksmod.screen.generator.TitaniumHeatGeneratorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TitaniumHeatGeneratorBlockEntity extends AbstractHeatGeneratorBlockEntity implements TitaniumTier {
    public TitaniumHeatGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TITANIUM_HEAT_GENERATOR, pos, state, GENERATION, ENERGY_STORAGE);
    }
    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        }
        Item item = fuel.getItem();
        return (FUELS.getOrDefault(item, 0)/45);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.chucksmod.titanium_heat_generator");
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        this.markDirty();
        return new TitaniumHeatGeneratorScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public int getGeneration() {
        return GENERATION;
    }

    @Override
    public int getMaxExtract() {
        return GEN_MAX_EXTRACT;
    }

    @Override
    public int getEnergyStorageCapacity() {
        return ENERGY_STORAGE;
    }
}
