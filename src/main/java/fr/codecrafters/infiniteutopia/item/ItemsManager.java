package fr.codecrafters.infiniteutopia.item;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.BlocksManager;
import fr.codecrafters.infiniteutopia.item.cooking.CooksCarver;
import fr.codecrafters.infiniteutopia.item.food.Consumable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemsManager {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteUtopia.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> COOKS_CARVER = ITEMS.register("cooks_carver",
            CooksCarver::new);

    public static final RegistryObject<Item> MINCED_RAW_BEEF = ITEMS.register("food/minced_raw_beef",
            () -> new Consumable(2, 0.2F));

    public static final RegistryObject<Item> COOKED_GROUND_BEEF = ITEMS.register("food/cooked_ground_beef/cooked_ground_beef",
            () -> new Consumable(5, 0.8F));


    public static final RegistryObject<Item> WATER_BOWL = ITEMS.register("food_tools/water_bowl",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ONION = ITEMS.register("plant/onion",
            () -> new Consumable(1, 0.2f));
    public static final RegistryObject<Item> ONION_SEEDS = ITEMS.register("plant/onion_seeds",
            () -> new ItemNameBlockItem(BlocksManager.ONION_PLANT.get(), new Item.Properties()));

}
