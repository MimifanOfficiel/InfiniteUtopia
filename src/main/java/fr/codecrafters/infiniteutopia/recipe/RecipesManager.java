package fr.codecrafters.infiniteutopia.recipe;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.recipe.cooking.CookingPotRecipe;
import fr.codecrafters.infiniteutopia.recipe.cooking.CuttingBoardRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipesManager {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, InfiniteUtopia.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CuttingBoardRecipe>> CUTTING_BOARD_SERIALIZER =
            SERIALIZERS.register("cutting_board", () -> CuttingBoardRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CookingPotRecipe>> COOKING_POT_SERIALIZER =
            SERIALIZERS.register("pot_cooking", () -> CookingPotRecipe.Serializer.INSTANCE);

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
    }
}
