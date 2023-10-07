package fr.codecrafters.infiniteutopia.integration;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.BlocksManager;
import fr.codecrafters.infiniteutopia.recipe.cooking.CookingPotRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
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

import java.util.List;

public class CookingPotRecipeCategory implements IRecipeCategory<CookingPotRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(InfiniteUtopia.MOD_ID, "pot_cooking");
    public static final ResourceLocation TEXTURE = new ResourceLocation(InfiniteUtopia.MOD_ID,
            "textures/gui/cooking_pot_gui.png");
    private final IDrawable background;
    private final IDrawable icon;

    public CookingPotRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlocksManager.COOKING_POT.get()));
    }

    @Override
    public RecipeType<CookingPotRecipe> getRecipeType() {
        return new RecipeType<>(CookingPotRecipeCategory.UID, CookingPotRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.infinite_utopia.cooking_pot");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CookingPotRecipe recipe, @NotNull IFocusGroup focuses) {

        if(recipe.getIngredients().get(0) != null) builder.addSlot(RecipeIngredientRole.INPUT, 26, 16).addIngredients(recipe.getIngredients().get(0));
        if(recipe.getIngredients().get(1) != null) builder.addSlot(RecipeIngredientRole.INPUT, 44, 16).addIngredients(recipe.getIngredients().get(1));
        if(recipe.getIngredients().get(2) != null) builder.addSlot(RecipeIngredientRole.INPUT, 62, 16).addIngredients(recipe.getIngredients().get(2));
        if(recipe.getIngredients().get(3) != null) builder.addSlot(RecipeIngredientRole.INPUT, 26, 34).addIngredients(recipe.getIngredients().get(3));
        if(recipe.getIngredients().get(4) != null) builder.addSlot(RecipeIngredientRole.INPUT, 44, 34).addIngredients(recipe.getIngredients().get(4));
        if(recipe.getIngredients().get(5) != null) builder.addSlot(RecipeIngredientRole.INPUT, 62, 34).addIngredients(recipe.getIngredients().get(5));

        builder.addSlot(RecipeIngredientRole.INPUT, 26, 56).
                addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getFluid()))
                .setFluidRenderer(8000, false, 51, 16);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 26).addItemStack(recipe.getResultItem(null));

    }
}
