package fr.codecrafters.infiniteutopia.block.cooking.entity;

import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import fr.codecrafters.infiniteutopia.item.ItemsManager;
import fr.codecrafters.infiniteutopia.screen.cooking_pot.CookingPotMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.network.chat.Component;
import org.joml.Random;

import javax.annotation.Nonnull;

public class CookingPotEntity extends BlockEntity implements MenuProvider {

    private ItemStackHandler itemStackHandler = new ItemStackHandler(9){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyHandler = LazyOptional.empty();

    public CookingPotEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesManager.CUTTING_BOARD_ENTITY.get(), pPos, pBlockState);
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("block.infinite_utopia.cooking_pot");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CookingPotMenu(pContainerId, pPlayerInventory, this);
    }

    @Nonnull
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemStackHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pstate, CookingPotEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)){
            craftItem(pBlockEntity);
        }
    }

    private static void craftItem(CookingPotEntity pBlockEntity){
        pBlockEntity.itemStackHandler.extractItem(0, 1, false);
        pBlockEntity.itemStackHandler.extractItem(1, 1, false);
        pBlockEntity.itemStackHandler.getStackInSlot(2).hurt(1, (RandomSource) new Random(), null);

        pBlockEntity.itemStackHandler.setStackInSlot(3, new ItemStack(ItemsManager.WATER_BOWL.get(),
                pBlockEntity.itemStackHandler.getStackInSlot(3).getCount() + 1));
    }

    private static boolean hasRecipe(CookingPotEntity entity){
        //boolean hasItemInWaterSlot = entity.itemStackHandler.getStackInSlot(0).equals()
        boolean hasItemInFirstSlot = entity.itemStackHandler.getStackInSlot(1).getItem() != null;
        boolean hasItemInSecondSlot = entity.itemStackHandler.getStackInSlot(2).getItem() != null;
        return hasItemInFirstSlot && hasItemInSecondSlot;
    }

    private static boolean hasNotReachedStackLimit(CookingPotEntity entity) {
        return entity.itemStackHandler.getStackInSlot(3).getCount() < entity.itemStackHandler.getStackInSlot(3).getMaxStackSize();
    }

}

