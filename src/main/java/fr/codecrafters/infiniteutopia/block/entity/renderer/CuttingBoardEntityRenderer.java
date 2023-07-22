package fr.codecrafters.infiniteutopia.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
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

        for (int i = 0; i < itemStack.getCount(); i++) {
            renderItem(itemStack, pPoseStack, pBlockEntity, pBuffer, i + 1);
        }
    }

    private void renderItem(ItemStack itemStack, PoseStack pPoseStack, CuttingBoardEntity entity, MultiBufferSource pBuffer, int position) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        pPoseStack.pushPose();
        pPoseStack.translate(0.5, (0.2 + (0.01 * position)), 0.5);
        pPoseStack.scale(0.6f, 0.6f, 0.6f);
        pPoseStack.rotateAround(new Quaternionf(new AxisAngle4f((float) (Math.PI/2), 1, 0, 0)), 0F, 0F, 0F);

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
