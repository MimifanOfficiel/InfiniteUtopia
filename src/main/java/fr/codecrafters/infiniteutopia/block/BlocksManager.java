package fr.codecrafters.infiniteutopia.block;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.chemistry.Microscope;
import fr.codecrafters.infiniteutopia.block.chemistry.MolecularDisassembler;
import fr.codecrafters.infiniteutopia.block.cooking.CookingPot;
import fr.codecrafters.infiniteutopia.block.cooking.CuttingBoard;
import fr.codecrafters.infiniteutopia.item.ItemsManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlocksManager {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InfiniteUtopia.MOD_ID);


    public static final RegistryObject<Block> CUTTING_BOARD = registerBlock("cutting_board",
            CuttingBoard::new);

    public static final RegistryObject<Block> MICROSCOPE = registerBlock("microscope",
            Microscope::new);

    public static final RegistryObject<Block> MOLECULAR_DISASSEMBLER = registerBlock("molecular_disassembler",
            MolecularDisassembler::new);

    public static final RegistryObject<Block> COOKING_POT = registerBlock("cooking_pot",
            CookingPot::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, new Item.Properties());
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Item.Properties properties) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, properties);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, Item.Properties properties) {
        return ItemsManager.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
