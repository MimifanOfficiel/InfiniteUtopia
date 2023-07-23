package fr.codecrafters.infiniteutopia.item;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.BlocksManager;
import fr.codecrafters.infiniteutopia.block.entity.Microscope;
import fr.codecrafters.infiniteutopia.item.elements.ChemicalElement;
import fr.codecrafters.infiniteutopia.item.elements.ElementsRegister;
import fr.codecrafters.infiniteutopia.item.food.Consumable;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabsManager {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InfiniteUtopia.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TOOLS_TAB = CREATIVE_MOD_TABS.register("tools_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsManager.COOKS_CARVER.get()))
                    .title(Component.translatable("creativetab.tools_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ItemsManager.COOKS_CARVER.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB = CREATIVE_MOD_TABS.register("blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BlocksManager.CUTTING_BOARD.get()))
                    .title(Component.translatable("creativetab.blocks_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(BlocksManager.CUTTING_BOARD.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> CHEMICAL_TAB = CREATIVE_MOD_TABS.register("chemical_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ElementsRegister.CARBON.get()))
                    .title(Component.translatable("creativetab.chemical_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(BlocksManager.MICROSCOPE.get());

                        for (RegistryObject<Item> entry : ItemsManager.ITEMS.getEntries()) {
                            if (entry.isPresent() && (entry.get()) instanceof ChemicalElement) {
                                pOutput.accept(entry.get());
                            }
                        }
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> FOODS_TAB = CREATIVE_MOD_TABS.register("foods_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsManager.MINCED_RAW_BEEF.get()))
                    .title(Component.translatable("creativetab.food_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        for(RegistryObject<Item> entry : ItemsManager.ITEMS.getEntries()){
                            if (entry.isPresent() && (entry.get()) instanceof Consumable) {
                                pOutput.accept(entry.get());
                            }
                        }
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }


}
