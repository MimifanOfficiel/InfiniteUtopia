package fr.codecrafters.infiniteutopia.block;

import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import fr.codecrafters.infiniteutopia.block.entity.CuttingBoardEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CuttingBoard extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE = Shapes.box(0.125, 0, 0.25, 0.875, 0.125, 0.75);

    private static final VoxelShape ROTATED_SHAPE = Shapes.box(0.25, 0, 0.125, 0.75, 0.125, 0.875);

    public CuttingBoard() {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                .sound(SoundType.WOOD).dynamicShape().noOcclusion());
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
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH, SOUTH -> SHAPE;
            case EAST, WEST -> ROTATED_SHAPE;
            default -> super.getShape(pState, pLevel, pPos, pContext);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CuttingBoardEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        // TODO: check for recipe
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            ItemStack itemInHand = player.getItemInHand(hand);

            if (blockEntity instanceof CuttingBoardEntity) {
                CuttingBoardEntity cuttingBoardEntity = (CuttingBoardEntity) blockEntity;

                if (player.isCrouching()) {
                    ItemStack itemStack = cuttingBoardEntity.getItem();

                    if (itemInHand != ItemStack.EMPTY) {
                        return InteractionResult.FAIL;
                    }

                    if (itemStack != ItemStack.EMPTY) {
                        if (!player.getInventory().add(itemStack)) {
                            player.drop(itemStack, false);
                        }

                        cuttingBoardEntity.setItem(ItemStack.EMPTY);
                        return InteractionResult.SUCCESS;
                    }
                } else {
                    ItemStack itemStack = cuttingBoardEntity.getItem();

                    if (itemStack != ItemStack.EMPTY) {
                        return InteractionResult.FAIL;
                    }

                    if (itemInHand == ItemStack.EMPTY) {
                        return InteractionResult.FAIL;
                    }

                    cuttingBoardEntity.setItem(itemInHand);
                    player.setItemInHand(hand, ItemStack.EMPTY);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CuttingBoardEntity) {
                CuttingBoardEntity cuttingBoardEntity = (CuttingBoardEntity) blockEntity;
                ItemStack itemStack = cuttingBoardEntity.getItem();

                if (itemStack != ItemStack.EMPTY) {
                    pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), itemStack));
                }
            }
        }
    }
}
