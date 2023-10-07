package fr.codecrafters.infiniteutopia.block.cooking.entity;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import fr.codecrafters.infiniteutopia.networking.Messages;
import fr.codecrafters.infiniteutopia.networking.packet.ItemStackSyncS2CPacket;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class CuttingBoardEntity extends BlockEntity {

    @Getter
    private ItemStackHandler handler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide) {
                Messages.sendToClients(new ItemStackSyncS2CPacket(this, worldPosition));
            }
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public CuttingBoardEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesManager.CUTTING_BOARD_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> handler);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(handler.getSlots());
        for (int i = 0; i < handler.getSlots(); i++) {
            inventory.setItem(i, handler.getStackInSlot(i));
            handler.setStackInSlot(i, ItemStack.EMPTY);
        }

        if (this.level == null) return;

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void setHandler(ItemStackHandler newHandler) {
        for (int i = 0; i < newHandler.getSlots(); i++) {
            handler.setStackInSlot(i, newHandler.getStackInSlot(i));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        CompoundTag compound = new CompoundTag();
        compound.put("cutting_item", handler.serializeNBT());
        pTag.put(InfiniteUtopia.MOD_ID, compound);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        CompoundTag compound = pTag.getCompound(InfiniteUtopia.MOD_ID);
        handler.deserializeNBT(compound.getCompound("cutting_item"));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    public ItemStack getItem() {
        return this.handler.getStackInSlot(0);
    }

    public void setItem(ItemStack stack) {
        this.handler.setStackInSlot(0, stack);
    }
}
