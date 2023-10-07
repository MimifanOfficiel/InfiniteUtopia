package fr.codecrafters.infiniteutopia.integration;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.BlocksManager;
import fr.codecrafters.infiniteutopia.recipe.cooking.CuttingBoardRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CuttingBoardRecipeCategory implements IRecipeCategory<CuttingBoardRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(InfiniteUtopia.MOD_ID, "cutting");
    public static final ResourceLocation TEXTURE = new ResourceLocation(InfiniteUtopia.MOD_ID,
            "textures/gui/cutting_board_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public CuttingBoardRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlocksManager.CUTTING_BOARD.get()));
    }

    @Override
    public @NotNull RecipeType<CuttingBoardRecipe> getRecipeType() {
        return new RecipeType<>(CuttingBoardRecipeCategory.UID, CuttingBoardRecipe.class);
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.infinite_utopia.cutting_board");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CuttingBoardRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 25, 30).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.CATALYST, 55, 30).addIngredients(recipe.getTool());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 130, 30).addItemStack(recipe.getResultItem());
    }
}
