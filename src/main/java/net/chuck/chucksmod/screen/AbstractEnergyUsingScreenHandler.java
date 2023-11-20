package net.chuck.chucksmod.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public abstract class AbstractEnergyUsingScreenHandler extends ScreenHandler {
    protected final World world;
    protected final Inventory inventory;
    protected final PropertyDelegate propertyDelegate;
    protected AbstractEnergyUsingScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity entity,
                                               PropertyDelegate delegate, ScreenHandlerType type, int invSize) {
        super(type, syncId);
        checkSize(((Inventory) entity), invSize);
        this.inventory = (Inventory) entity;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        addPlayerHotbar(playerInventory);
        addPlayerInventory(playerInventory);
        addProperties(delegate);
        world = playerInventory.player.getWorld();
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    public void addPlayerInventory(PlayerInventory inventory) {
        for(int i=0;i<3;++i) {
            for(int j=0;j<9;++j) {
                this.addSlot(new Slot(inventory, j + i*9 + 9, 8 + j*18, 84 + i*18));
            }
        }
    }

    public void addPlayerHotbar(PlayerInventory inventory){
        for(int i=0;i<9;++i) {
            this.addSlot(new Slot(inventory, i, 8 + i*18, 142));
        }
    }
}
