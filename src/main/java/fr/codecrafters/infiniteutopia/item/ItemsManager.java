package fr.codecrafters.infiniteutopia.item;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.item.cooking.CooksCarver;
import fr.codecrafters.infiniteutopia.item.food.Consumable;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


@SuppressWarnings("unused")
public class ItemsManager {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteUtopia.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> COOKS_CARVER = ITEMS.register("cooks_carver",
            CooksCarver::new);

    public static final RegistryObject<Item> MINCED_RAW_BEEF = ITEMS.register("food/minced_raw_beef",
            () -> new Consumable(2, 0.2F));




}
