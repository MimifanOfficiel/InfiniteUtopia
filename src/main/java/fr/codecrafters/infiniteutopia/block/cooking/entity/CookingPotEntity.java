package fr.codecrafters.infiniteutopia.block.cooking.entity;

import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import fr.codecrafters.infiniteutopia.item.ItemsManager;
import fr.codecrafters.infiniteutopia.recipe.cooking.CookingPotRecipe;
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
import net.minecraft.world.inventory.ContainerData;
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
import java.util.Optional;

public class CookingPotEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(9){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public CookingPotEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesManager.CUTTING_BOARD_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData(){

            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> CookingPotEntity.this.progress;
                    case 1 -> CookingPotEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> CookingPotEntity.this.progress = pValue;
                    case 1 -> CookingPotEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }


    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.infinite_utopia.cooking_pot");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new CookingPotMenu(pContainerId, pPlayerInventory, this, this.data);
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
        nbt.putInt("cooking_pot_progress", progress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("cooking_pot_progress");
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pstate, CookingPotEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)){
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pstate);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) craftItem(pBlockEntity);
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pstate);
        }
    }

    private static boolean hasRecipe(CookingPotEntity entity){
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
        for (int i = 0; i < entity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }

        assert level != null;
        Optional<CookingPotRecipe> match = level.getRecipeManager()
                .getRecipeFor(CookingPotRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem(null));
                //&& hasWaterInWaterSlot(entity);
    }

    private static boolean hasWaterInWaterSlot(CookingPotEntity entity) {
        return entity.itemStackHandler.getStackInSlot(6).getItem().equals(ItemsManager.WATER_BOWL.get());
    }

    private static void craftItem(CookingPotEntity pBlockEntity){
        Level level = pBlockEntity.level;
        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemStackHandler.getSlots());
        for (int i = 0; i < pBlockEntity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemStackHandler.getStackInSlot(i));
        }

        assert level != null;
        Optional<CookingPotRecipe> match = level.getRecipeManager()
                .getRecipeFor(CookingPotRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            pBlockEntity.itemStackHandler.extractItem(0, 1, false);
            pBlockEntity.itemStackHandler.extractItem(1, 1, false);
            pBlockEntity.itemStackHandler.extractItem(2, 1, false);
            pBlockEntity.itemStackHandler.extractItem(3, 1, false);
            pBlockEntity.itemStackHandler.extractItem(4, 1, false);
            pBlockEntity.itemStackHandler.extractItem(5, 1, false);
            //pBlockEntity.itemStackHandler.getStackInSlot(2).hurt(1, (RandomSource) new Random(), null);

            pBlockEntity.itemStackHandler.setStackInSlot(8, new ItemStack(match.get().getResultItem(null).getItem(),
                    pBlockEntity.itemStackHandler.getStackInSlot(8).getCount() + 1));
        }

        pBlockEntity.resetProgress();

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(8).getItem() == output.getItem() || inventory.getItem(8).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory){
        return inventory.getItem(8).getMaxStackSize() > inventory.getItem(8).getCount();
    }

}

