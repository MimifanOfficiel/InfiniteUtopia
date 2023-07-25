package fr.codecrafters.infiniteutopia.event;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.block.chemistry.renderer.MolecularDisassemblerEntityRenderer;
import fr.codecrafters.infiniteutopia.block.entity.BlockEntitiesManager;
import fr.codecrafters.infiniteutopia.block.cooking.renderer.CuttingBoardEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvent {

    @Mod.EventBusSubscriber(modid = InfiniteUtopia.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(BlockEntitiesManager.CUTTING_BOARD_ENTITY.get(), CuttingBoardEntityRenderer::new);
            event.registerBlockEntityRenderer(BlockEntitiesManager.MOLECULAR_DISASSEMBLER_ENTITY.get(), MolecularDisassemblerEntityRenderer::new);
        }
    }

}
