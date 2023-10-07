package fr.codecrafters.infiniteutopia.block.cooking;

import fr.codecrafters.infiniteutopia.block.cooking.entity.CuttingBoardEntity;
import fr.codecrafters.infiniteutopia.recipe.cooking.CuttingBoardRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
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

import java.util.Optional;

public class CuttingBoard extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

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
    public @NotNull BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return this.getShape(pState.getValue(FACING));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    public VoxelShape getShape(Direction direction) {
        switch (direction) {
            case WEST -> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.0625, 0.375, 0.0625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.1875, 0.8125, 0.0625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.0625, 0.8125, 0.0625, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.0625, 0.625, 0.0625, 0.125), BooleanOp.OR);

                return shape;
            }
            case EAST -> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.0625, 0.8125, 0.0625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.0625, 0.625, 0.0625, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.8125, 0.375, 0.0625, 0.9375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.875, 0.625, 0.0625, 0.9375), BooleanOp.OR);

                return shape;
            }
            case NORTH -> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.1875, 0.9375, 0.0625, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.375, 0.8125, 0.0625, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.625, 0.9375, 0.0625, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.875, 0, 0.375, 0.9375, 0.0625, 0.625), BooleanOp.OR);

                return shape;
            }
            case SOUTH -> {
                VoxelShape shape = Shapes.empty();
                shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.625, 0.9375, 0.0625, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.9375, 0.0625, 0.625), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.1875, 0.1875, 0.0625, 0.375), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.375, 0.125, 0.0625, 0.625), BooleanOp.OR);

                return shape;
            }
        }

        return Shapes.empty();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new CuttingBoardEntity(pPos, pState);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            ItemStack itemInHand = player.getItemInHand(hand);

            if (blockEntity instanceof CuttingBoardEntity cuttingBoardEntity) {

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
                    if (hasRecipe(cuttingBoardEntity, itemInHand)) {
                        craftItem(cuttingBoardEntity, itemInHand, player);
                        return InteractionResult.SUCCESS;
                    }

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
    public void onRemove(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pNewState, boolean pIsMoving) {
        if (!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CuttingBoardEntity cuttingBoardEntity) {
                cuttingBoardEntity.drops();
            }
        }
    }

    private static boolean hasRecipe(CuttingBoardEntity e, ItemStack itemInHand) {
        Level level = e.getLevel();
        SimpleContainer container = new SimpleContainer(e.getHandler().getSlots() + 1);
        container.setItem(0, e.getItem());
        container.setItem(1, itemInHand);

        if (level == null) return false;

        Optional<CuttingBoardRecipe> match = level.getRecipeManager().getRecipeFor(CuttingBoardRecipe.Type.INSTANCE, container, level);

        return match.isPresent();
    }

    private static void craftItem(CuttingBoardEntity e, ItemStack itemInHand, Player player) {
        Level level = e.getLevel();
        SimpleContainer container = new SimpleContainer(e.getHandler().getSlots() + 1);
        container.setItem(0, e.getItem());
        container.setItem(1, itemInHand);

        if (level == null) return;

        Optional<CuttingBoardRecipe> match = level.getRecipeManager().getRecipeFor(CuttingBoardRecipe.Type.INSTANCE, container, level);

        if (match.isPresent()) {
            e.getHandler().extractItem(0, 1, false);

            if (!player.isCreative())
                itemInHand.hurt(1, level.random, null);

            if (itemInHand.getDamageValue() >= itemInHand.getMaxDamage())
                player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);

            ItemStack result = match.get().getResultItem();
            if (!level.isClientSide) {
                ItemEntity itemEntity = new ItemEntity(level, e.getBlockPos().getX() + 0.5, e.getBlockPos().getY() + 0.2, e.getBlockPos().getZ() + 0.5, result);
                itemEntity.setPickUpDelay(10);
                level.addFreshEntity(itemEntity);
            }
        }
    }
}
