package fr.codecrafters.infiniteutopia.block.entity;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.BlocksManager;
import fr.codecrafters.infiniteutopia.block.cooking.CuttingBoardEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesManager {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, InfiniteUtopia.MOD_ID);

    public static final RegistryObject<BlockEntityType<CuttingBoardEntity>> CUTTING_BOARD_ENTITY = BLOCK_ENTITIES.register("cutting_board_entity",
            () -> BlockEntityType.Builder.of(CuttingBoardEntity::new, BlocksManager.CUTTING_BOARD.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
