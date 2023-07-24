package fr.codecrafters.infiniteutopia.block.cooking;

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

public class CookingPot extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public CookingPot() {
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
                shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.0625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.4375, 0.0625, 0.9375, 0.5625, 0.125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.4375, 0.875, 0.9375, 0.5625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.4375, 0.125, 0.125, 0.5625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.875, 0.4375, 0.125, 0.9375, 0.5625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(-0.0625, 0.5, 0.625, 0.0625, 0.625, 0.6875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(-0.0625, 0.5, 0.3125, 0.0625, 0.625, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(-0.0625, 0.5, 0.375, 0.0625, 0.625, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.9375, 0.5, 0.625, 1.0625, 0.625, 0.6875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.9375, 0.5, 0.3125, 1.0625, 0.625, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.9375, 0.5, 0.375, 1.0625, 0.625, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.5, 0.8125, 0.875, 0.5625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.5, 0.125, 0.875, 0.5625, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0.5, 0.1875, 0.875, 0.5625, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.5, 0.1875, 0.1875, 0.5625, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.0625, 0.125, 0.1875, 0.5, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0.0625, 0.125, 0.875, 0.5, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.8125, 0.8125, 0.5, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.125, 0.8125, 0.5, 0.1875), BooleanOp.OR);

                return shape;
            }
            case EAST, WEST -> {

                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.0625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.875, 0.4375, 0.0625, 0.9375, 0.5625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0.4375, 0.0625, 0.125, 0.5625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.4375, 0.0625, 0.875, 0.5625, 0.125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.4375, 0.875, 0.875, 0.5625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.3125, 0.5, -0.0625, 0.375, 0.625, 0.0625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.625, 0.5, -0.0625, 0.6875, 0.625, 0.0625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.5, -0.0625, 0.625, 0.625, 0.0625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.3125, 0.5, 0.9375, 0.375, 0.625, 1.0625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.625, 0.5, 0.9375, 0.6875, 0.625, 1.0625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0.5, 0.9375, 0.625, 0.625, 1.0625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.5, 0.125, 0.1875, 0.5625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0.5, 0.125, 0.875, 0.5625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0.5, 0.8125, 0.8125, 0.5625, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0.5, 0.125, 0.8125, 0.5625, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.5, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.0625, 0.8125, 0.875, 0.5, 0.875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.125, 0.0625, 0.1875, 0.1875, 0.5, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0.0625, 0.1875, 0.875, 0.5, 0.8125), BooleanOp.OR);

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
