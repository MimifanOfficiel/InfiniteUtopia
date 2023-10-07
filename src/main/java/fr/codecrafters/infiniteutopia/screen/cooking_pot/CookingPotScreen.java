package fr.codecrafters.infiniteutopia.screen.cooking_pot;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.screen.renderer.FluidTankRenderer;
import fr.codecrafters.infiniteutopia.util.MouseUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CookingPotScreen extends AbstractContainerScreen<CookingPotMenu>  {

    public static final ResourceLocation TEXTURE = new ResourceLocation(InfiniteUtopia.MOD_ID, "textures/gui/cooking_pot_gui.png");
    private FluidTankRenderer renderer;

    public CookingPotScreen(CookingPotMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
    }

    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(8000, true, 52, 16);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageWidth);

        renderProgressArrow(pGuiGraphics, x, y);

        renderer.render(pGuiGraphics.pose(), x+26, y+56, menu.getBlockEntity().getFluidStack());
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 101, y + 24, 181, 18, 9, menu.getScaledProgress());
        }
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) /2;
        int y = (height - imageHeight) /2;

        renderFluidTooltipArea(pGuiGraphics, pMouseX, pMouseY, x, y, menu.getBlockEntity().getFluidStack(), 26, 55, renderer);
    }

    private void renderFluidTooltipArea(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y,
                                        FluidStack stack, int offsetX, int offsetY, FluidTankRenderer renderer) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, offsetX, offsetY, renderer)) {
            guiGraphics.renderTooltip(this.font, renderer.getTooltip(stack, TooltipFlag.Default.NORMAL),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }


    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, FluidTankRenderer renderer) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
