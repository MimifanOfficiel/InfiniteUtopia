package fr.codecrafters.infiniteutopia.block.chemistry.entity;

import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MolecularDisassemblerEntity extends BlockEntity {

    private FluidTank fluidTank = new FluidTank(1024000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid().isSame(Fluids.WATER);
        }
    };

    private LazyOptional<IFluidHandler> lazyFluidTank = LazyOptional.empty();

    public MolecularDisassemblerEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesManager.MOLECULAR_DISASSEMBLER_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return super.getCapability(cap, side);
    }

    public void setFluid(FluidStack stack) {
        this.fluidTank.setFluid(stack);
    }

    public FluidStack getFluid() {
        return this.fluidTank.getFluid();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyFluidTank = LazyOptional.of(() -> fluidTank);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyFluidTank.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt = fluidTank.writeToNBT(nbt);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        fluidTank.readFromNBT(nbt);
    }
}
