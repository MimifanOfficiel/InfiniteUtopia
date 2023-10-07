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
import org.jetbrains.annotations.NotNull;

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
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageWidth);

        if(menu.isCrafting()){
            pGuiGraphics.blit(TEXTURE, x+101, y+24, 181, 18, 9, menu.getScaledProgress());
            //pGuiGraphics.blit(TEXTURE, x+44, y+56, 176, 0, 20, menu.getScaledProgress());
        }

        renderer.render(pGuiGraphics.pose(), x+26, y+56, menu.getFluid());
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

        rendererFluidAreaTooltip(pGuiGraphics, pMouseX, pMouseY, x, y);
    }

    private void rendererFluidAreaTooltip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y)){
            /* TODO RENDER TOOLTIP BECAUSE IT IS NOT WORKING AT THE MOMENT
                LA CON DE TES MORTS
                ÇA M'A SAOULÉ
                (C'était dans le fluid handler, dans la playlist
                de Kaupenjoe forge modding 1.19, episode 26)
             */
        }
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + 26, y + 56, renderer.getWidth(), renderer.getHeight());
    }
}
