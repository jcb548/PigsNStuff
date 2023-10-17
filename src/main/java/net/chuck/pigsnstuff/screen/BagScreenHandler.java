package net.chuck.pigsnstuff.screen;

import net.chuck.pigsnstuff.item.ModItems;
import net.chuck.pigsnstuff.item.custom.BagItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class BagScreenHandler extends ScreenHandler implements InventoryChangedListener {
    public static final String ITEMS_KEY = "Items";
    protected final SimpleInventory inventory;
    protected final ItemStack bagItemStack;
    public BagScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, buf.readItemStack());
    }

    public BagScreenHandler(int syncId, PlayerInventory playerInventory, ItemStack itemStack){
        super(ModScreenHandlers.BAG_SCREEN_HANDLER, syncId);
        inventory = new SimpleInventory(BagItem.INV_SIZE);
        bagItemStack = itemStack;
        if(itemStack.getNbt() != null) {
            Inventories.readNbt(itemStack.getNbt(), inventory.stacks);
        } else {
            NbtCompound newNbt = new NbtCompound();
            Inventories.writeNbt(newNbt, inventory.stacks, false);
            bagItemStack.setNbt(newNbt);
        }
        inventory.onOpen(playerInventory.player);
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addBagInventory();
        inventory.addListener(this);
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot < BagItem.INV_SIZE ?
                    !this.insertItem(itemStack2, BagItem.INV_SIZE, this.slots.size(), true)
                    : !this.insertItem(itemStack2, 0, BagItem.INV_SIZE, false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
        }
        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    public void addPlayerInventory(PlayerInventory playerInventory) {
        for(int i=0;i<3;++i) {
            for(int j=0;j<9;++j) {
                this.addSlot(new Slot(playerInventory, j + i*9 + 9, 8 + j*18, 51 + i*18));
            }
        }
    }
    public void addPlayerHotbar(PlayerInventory playerInventory){
        for(int i=0;i<9;++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i*18, 109));
        }
    }

    private void addBagInventory(){
        for(int i=0; i<BagItem.INV_SIZE;++i){
            this.addSlot(new Slot(inventory, i, 8+ i*18, 20));
        }
    }

    @Override
    public void onInventoryChanged(Inventory sender) {
        NbtCompound newNbt = new NbtCompound();
        Inventories.writeNbt(newNbt, inventory.stacks, false);
        bagItemStack.setNbt(newNbt);
    }
}
