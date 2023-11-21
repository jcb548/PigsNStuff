package net.chuck.chucksmod.block.entity;

import net.chuck.chucksmod.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class AbstractEnergyUsingBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory,
        ImplementedInventory {
    public static final int INPUT_SLOT = 0;
    public final SimpleEnergyStorage energyStorage;
    protected final DefaultedList<ItemStack> inventory;
    public AbstractEnergyUsingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
                                          int invSize, int energyStorageSize, int maxInsertExtract) {
        super(type, pos, state);
        this.inventory = DefaultedList.ofSize(invSize, ItemStack.EMPTY);
        energyStorage = new SimpleEnergyStorage(energyStorageSize, maxInsertExtract, maxInsertExtract){
            @Override
            protected void onFinalCommit() {
                markDirty();
                if(!world.isClient()){
                    PacketByteBuf data = PacketByteBufs.create();
                    data.writeLong(amount);
                    data.writeBlockPos(getPos());

                    for(ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())){
                        ServerPlayNetworking.send(player, ModMessages.ENERGY_SYNC, data);
                    }
                }
            }
        };
    }
    public void setEnergyLevel(long energyLevel){
        this.energyStorage.amount = energyLevel;
    }

    protected void extractEnergy() {
        try(Transaction transaction = Transaction.openOuter()){
            this.energyStorage.extract(this.getEnergyUsage(), transaction);
            transaction.commit();
        }
    }
    protected boolean hasEnoughEnergy() {
        return this.energyStorage.amount >= getEnergyUsage();
    }

    protected abstract int getEnergyUsage();
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
        buf.writeLong(this.energyStorage.amount);
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        super.writeNbt(nbt);
        nbt.putLong("energy_block.energy", energyStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        energyStorage.amount= nbt.getLong("energy_block.energy");
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }
}
