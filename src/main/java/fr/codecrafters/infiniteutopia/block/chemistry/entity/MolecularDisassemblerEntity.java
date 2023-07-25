package fr.codecrafters.infiniteutopia.block.chemistry.entity;

import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.FluidHandlerBlockEntity;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class MolecularDisassemblerEntity extends BlockEntity {

    private FluidTank fluidHandler = new FluidTank(1024000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid().isSame(Fluids.WATER);
        }
    };

    public MolecularDisassemblerEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesManager.MOLECULAR_DISASSEMBLER_ENTITY.get(), pPos, pBlockState);
    }
}
