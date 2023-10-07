package fr.codecrafters.infiniteutopia.event;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.recipe.cooking.CookingPotRecipe;
import fr.codecrafters.infiniteutopia.recipe.cooking.CuttingBoardRecipe;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = InfiniteUtopia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBusEvent {

    @SubscribeEvent
    public void registerRecipeTypes(RegisterEvent e) {
        ForgeRegistries.RECIPE_TYPES.register(CuttingBoardRecipe.Type.ID, CuttingBoardRecipe.Type.INSTANCE);
        ForgeRegistries.RECIPE_TYPES.register(CookingPotRecipe.Type.ID, CookingPotRecipe.Type.INSTANCE);
    }
}
