package net.chuck.chucksmod.screen.bag;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import org.jetbrains.annotations.Nullable;

public class BagScreenHandlerFactory7x2 extends AbstractBagScreenHandlerFactory{
    public BagScreenHandlerFactory7x2(ItemStack itemStack) {
        super(itemStack);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BagScreenHandler7x2(syncId, playerInventory, itemStack);
    }
}
