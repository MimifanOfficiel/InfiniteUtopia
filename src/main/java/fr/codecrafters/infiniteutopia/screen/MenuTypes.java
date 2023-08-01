package fr.codecrafters.infiniteutopia.screen;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.screen.cooking_pot.CookingPotMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, InfiniteUtopia.MOD_ID);

    public static final RegistryObject<MenuType<CookingPotMenu>> COOKING_POT_MENU =
            registerMenuType(CookingPotMenu::new, "cooking_pot_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {

        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }


}
