package fr.codecrafters.infiniteutopia.enchantments.enchants.telekinesis;

import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import fr.codecrafters.infiniteutopia.enchantments.EnchantmentsManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfiniteUtopia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class MiningHandler {

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent event) {
        //Block block = event.getState().getBlock();
        //BlockPos pos = event.getPos();
        if( (event.getPlayer() != null) && (EnchantmentHelper.getEnchantmentLevel(EnchantmentsManager.TELEKINESIS.get(), event.getPlayer()) > 0) ){
            System.out.println("Ui");
        }
    }

}
