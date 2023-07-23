package fr.codecrafters.infiniteutopia;

import com.mojang.logging.LogUtils;
import fr.codecrafters.infiniteutopia.block.BlocksManager;
import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import fr.codecrafters.infiniteutopia.enchantments.EnchantmentsManager;
import fr.codecrafters.infiniteutopia.event.EnchantmentEventHandler;
import fr.codecrafters.infiniteutopia.item.CreativeTabsManager;
import fr.codecrafters.infiniteutopia.item.ItemsManager;
import fr.codecrafters.infiniteutopia.item.elements.ElementsRegister;
import fr.codecrafters.infiniteutopia.networking.Messages;
import fr.codecrafters.infiniteutopia.recipe.RecipesManager;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(InfiniteUtopia.MOD_ID)
public class InfiniteUtopia {
    public static final String MOD_ID = "infinite_utopia";
    private static final Logger LOGGER = LogUtils.getLogger();

    public InfiniteUtopia() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        /* Items registration */
        ItemsManager.register(modEventBus);
        ElementsRegister.register(modEventBus);


        /* Blocks registration */
        BlocksManager.register(modEventBus);
        BlockEntitiesManager.register(modEventBus);


        /* Creative tabs registration */
        CreativeTabsManager.register(modEventBus);


        /* Recipes registration */
        RecipesManager.register(modEventBus);


        /* Enchantments registration */
        EnchantmentsManager.register(modEventBus);


        /* Events registration */
        MinecraftForge.EVENT_BUS.register(new EnchantmentEventHandler());


        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)  {
        event.enqueueWork(Messages::register);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
