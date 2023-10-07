package fr.codecrafters.infiniteutopia.integration;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.recipe.cooking.CookingPotRecipe;
import fr.codecrafters.infiniteutopia.recipe.cooking.CuttingBoardRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(InfiniteUtopia.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CuttingBoardRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CookingPotRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<CuttingBoardRecipe> cuttingBoardRecipes = recipeManager.getAllRecipesFor(CuttingBoardRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(CuttingBoardRecipeCategory.UID, CuttingBoardRecipe.class), cuttingBoardRecipes);

        registration.addRecipes(new RecipeType<>(CookingPotRecipeCategory.UID, CookingPotRecipe.class), recipeManager.getAllRecipesFor(CookingPotRecipe.Type.INSTANCE));
    }
}
