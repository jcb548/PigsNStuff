package net.chuck.chucksmod.screen.bag;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractBagScreenHandlerFactory implements ExtendedScreenHandlerFactory {
    protected final ItemStack itemStack;
    public AbstractBagScreenHandlerFactory(ItemStack itemStack){
        this.itemStack = itemStack;
    }
    @Override
    public Text getDisplayName() {
        return  Text.translatable("gui.chucksmod.bag");
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeItemStack(itemStack);
    }
}
