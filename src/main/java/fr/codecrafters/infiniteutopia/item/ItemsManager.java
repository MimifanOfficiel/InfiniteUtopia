package fr.codecrafters.infiniteutopia.item;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsManager {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteUtopia.MOD_ID);

    public static final RegistryObject<Item> COOKS_CARVER = ITEMS.register("cooks_carver",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



}
