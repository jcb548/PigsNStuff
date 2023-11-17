package net.chuck.chucksmod.screen.generator;

import net.chuck.chucksmod.block.entity.generator.AbstractHeatGeneratorBlockEntity;
import net.chuck.chucksmod.block.entity.generator.IronHeatGeneratorBlockEntity;
import net.chuck.chucksmod.screen.AbstractEnergyUsingScreenHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public abstract class AbstractGeneratorScreenHandler extends AbstractEnergyUsingScreenHandler {
    protected static final int INV_SIZE = 1;
    protected AbstractGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity entity,
                                             PropertyDelegate delegate, ScreenHandlerType type) {
        super(syncId, playerInventory, entity, delegate, type, INV_SIZE);
        this.addSlot(new Slot(inventory, AbstractHeatGeneratorBlockEntity.FUEL_SLOT, 80, 39));

    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()){
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if(invSlot < this.inventory.size()) {
                if(!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, IronHeatGeneratorBlockEntity.FUEL_SLOT, this.inventory.size(), false)){
                return ItemStack.EMPTY;
            }
            if(originalStack.isEmpty()){
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
    public boolean isBurning() {
        return propertyDelegate.get(IronHeatGeneratorBlockEntity.BURN_TIME_IDX) > 0;
    }

    public int getScaledBurning() {
        int burnTime = this.propertyDelegate.get(IronHeatGeneratorBlockEntity.BURN_TIME_IDX);
        int fuelTime = this.propertyDelegate.get(IronHeatGeneratorBlockEntity.FUEL_TIME_IDX);
        int burnFlameSize = 13;

        return fuelTime != 0 && burnTime != 0 ? burnTime * burnFlameSize / fuelTime : 0;
    }
}
