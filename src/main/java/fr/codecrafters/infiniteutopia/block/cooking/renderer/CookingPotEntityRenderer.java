package fr.codecrafters.infiniteutopia.block.cooking.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.codecrafters.infiniteutopia.block.cooking.entity.CookingPotEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class CookingPotEntityRenderer /*implements BlockEntityRenderer<CookingPotEntity>*/ {

    /* TODO MAKE THE FLUID RENDERER TO BE INSIDE THE COOKING POT

    public CookingPotEntityRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(CookingPotEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer fluidTankRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.65f, 0.5f);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
    }*/
}
