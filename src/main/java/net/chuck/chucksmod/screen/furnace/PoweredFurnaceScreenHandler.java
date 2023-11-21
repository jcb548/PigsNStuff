package net.chuck.chucksmod.screen.furnace;

import net.chuck.chucksmod.block.entity.AbstractEnergyCookerBlockEntity;
import net.chuck.chucksmod.block.entity.furnace.AbstractPoweredFurnaceBlockEntity;
import net.chuck.chucksmod.screen.AbstractEnergyCookerScreenHandler;
import net.chuck.chucksmod.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;

public class PoweredFurnaceScreenHandler extends AbstractEnergyCookerScreenHandler {
    public PoweredFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity entity,
                                       PropertyDelegate delegate, long energy) {
        super(syncId, playerInventory, entity, delegate, ModScreenHandlers.POWERED_FURNACE_SCREEN_HANDLER);
        this.blockEntity.setEnergyLevel(energy);
    }
    public PoweredFurnaceScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf){
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(AbstractPoweredFurnaceBlockEntity.DELEGATE_SIZE), buf.readLong());
    }
    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(AbstractEnergyCookerBlockEntity.PROGRESS_IDX);
        int maxProgress = this.propertyDelegate.get(AbstractEnergyCookerBlockEntity.MAX_PROGRESS_IDX);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    protected boolean isRecipeItem(ItemStack item) {
        return this.world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory(item),
                this.world).isPresent();
    }
}
