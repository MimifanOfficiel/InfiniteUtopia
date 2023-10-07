package fr.codecrafters.infiniteutopia.item;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.BlocksManager;
import fr.codecrafters.infiniteutopia.item.elements.AtomItem;
import fr.codecrafters.infiniteutopia.item.elements.AtomsRegister;
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
                        pOutput.accept(BlocksManager.COOKING_POT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> CHEMISTRY_TAB = CREATIVE_MOD_TABS.register("chemistry_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BlocksManager.MICROSCOPE.get()))
                    .title(Component.translatable("creativetab.chemical_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(BlocksManager.MICROSCOPE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ATOMS_TAB = CREATIVE_MOD_TABS.register("atoms_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AtomsRegister.CARBON.get()))
                    .title(Component.translatable("creativetab.chemical_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(BlocksManager.MOLECULAR_DISASSEMBLER.get());

                        for (RegistryObject<Item> entry : ItemsManager.ITEMS.getEntries()) {
                            if (entry.isPresent() && (entry.get()) instanceof AtomItem) {
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

    public static final RegistryObject<CreativeModeTab> COOKING_TAB = CREATIVE_MOD_TABS.register("cooking_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BlocksManager.COOKING_POT.get()))
                    .title(Component.translatable("creativetab.cooking_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(BlocksManager.COOKING_POT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> CROPS_TAB = CREATIVE_MOD_TABS.register("crops_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemsManager.ONION_SEEDS.get()))
                    .title(Component.translatable("creativetab.crops_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ItemsManager.ONION_SEEDS.get());
                    })
                    .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }


}
