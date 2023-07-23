package fr.codecrafters.infiniteutopia.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Microscope extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public Microscope() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().dynamicShape().requiresCorrectToolForDrops());
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return this.getShape(pState.getValue(FACING));
    }

    private VoxelShape getShape(Direction direction) {
        switch (direction) {
            case NORTH, SOUTH -> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.1875, 0.6875, 0.453125, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.3125, 0.453125, 0.3125, 0.6875, 0.921875, 0.703125), BooleanOp.OR);

                return shape;
            }
            case EAST, WEST -> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.18359375, 0, 0.31640625, 0.80859375, 0.453125, 0.69140625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.30859375, 0.453125, 0.31640625, 0.69921875, 0.921875, 0.69140625), BooleanOp.OR);

                return shape;
            }
        }
        
        return Shapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }
}
