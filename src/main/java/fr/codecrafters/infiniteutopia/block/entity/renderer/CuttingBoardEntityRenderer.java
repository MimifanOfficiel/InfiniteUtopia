package fr.codecrafters.infiniteutopia.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.codecrafters.infiniteutopia.block.CuttingBoard;
import fr.codecrafters.infiniteutopia.block.entity.CuttingBoardEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.Nullable;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Vector;

public class CuttingBoardEntityRenderer implements BlockEntityRenderer<CuttingBoardEntity> {
    public CuttingBoardEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CuttingBoardEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack itemStack = pBlockEntity.getItem();

        for (int i = 0; i < Math.min(itemStack.getCount(), 4); i++) {
            renderItem(itemStack, pPoseStack, pBlockEntity, pBuffer, i, itemStack.getCount() == 1);
        }
    }

    private void renderItem(ItemStack itemStack, PoseStack pPoseStack, CuttingBoardEntity entity, MultiBufferSource pBuffer, int position, boolean hasOnlyOneItem) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        int dx = 0, dz = 0;

        switch (entity.getBlockState().getValue(CuttingBoard.FACING)) {
            case NORTH, SOUTH -> dx = 1;
            case EAST, WEST -> dz = 1;
        }

        boolean isSouthOrWest = entity.getBlockState().getValue(CuttingBoard.FACING) == Direction.SOUTH || entity.getBlockState().getValue(CuttingBoard.FACING) == Direction.WEST;
        boolean isSouthOrEast = entity.getBlockState().getValue(CuttingBoard.FACING) == Direction.SOUTH || entity.getBlockState().getValue(CuttingBoard.FACING) == Direction.EAST;

        double stack = isSouthOrWest ? 0.75 : 0.25;
        double item = isSouthOrWest ? 0.4 : 0.6;

        double delta = isSouthOrEast ? 0.55 : 0.45;

        pPoseStack.pushPose();
        if (position == 0) {

            if (hasOnlyOneItem) {
                pPoseStack.translate(0.5, 0.08, 0.5);
            } else {
                pPoseStack.translate(dx == 0 ? delta : item, 0.08, dz == 0 ? delta : item);
            }

        } else {
            pPoseStack.translate(dx == 0 ? delta : stack, (0.08 + (0.03 * (position - 1))), dz == 0 ? delta : stack);
        }

        pPoseStack.scale(0.6f, 0.6f, 0.6f);
        pPoseStack.rotateAround(new Quaternionf(new AxisAngle4f((float) (Math.PI/2), 1, 0, 0)), 0F, 0F, 0F);

        switch (entity.getBlockState().getValue(CuttingBoard.FACING)) {
            case NORTH -> pPoseStack.rotateAround(new Quaternionf(new AxisAngle4f((float) 0, 0, 0, 1)), 0F, 0F, 0F);
            case EAST -> pPoseStack.rotateAround(new Quaternionf(new AxisAngle4f((float) (Math.PI/2), 0, 0, 1)), 0F, 0F, 0F);
            case SOUTH -> pPoseStack.rotateAround(new Quaternionf(new AxisAngle4f((float) ((Math.PI)), 0, 0, 1)), 0F, 0F, 0F);
            case WEST -> pPoseStack.rotateAround(new Quaternionf(new AxisAngle4f((float) (3 * (Math.PI/2)), 0, 0, 1)), 0F, 0F, 0F);
        }

        itemRenderer.renderStatic(
                itemStack,
                ItemDisplayContext.GROUND,
                getLightLevel(entity.getLevel(), entity.getBlockPos()),
                OverlayTexture.NO_OVERLAY,
                pPoseStack,
                pBuffer,
                entity.getLevel(),
                1
        );
        pPoseStack.popPose();
    }

    private int getLightLevel(@Nullable Level level, BlockPos blockPos) {
        if (level == null) return LightTexture.pack(15, 15);

        int bLight = level.getBrightness(LightLayer.BLOCK, blockPos);
        int sLight = level.getBrightness(LightLayer.SKY, blockPos);
        return LightTexture.pack(bLight, sLight);
    }
}
