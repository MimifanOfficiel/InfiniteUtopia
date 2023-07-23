package fr.codecrafters.infiniteutopia.event;

import fr.codecrafters.infiniteutopia.enchantments.EnchantmentsManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnchantmentEventHandler {

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();

        if(event.getPlayer().isCreative()) return;
        if( (event.getPlayer() != null) && (EnchantmentHelper.getEnchantmentLevel(EnchantmentsManager.TELEKINESIS.get(), event.getPlayer()) > 0) ){

            LevelAccessor level = event.getLevel();

            boolean isFull = false;
            if (level instanceof ServerLevel) {

                level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);

                for (ItemStack drop : Block.getDrops(event.getState(), (ServerLevel) level, event.getPos(), null)) {
                    // add drops to player inventory
                    if (!isFull) {
                        if (!event.getPlayer().getInventory().add(drop)) isFull = true;
                    }

                    if (isFull) level.addFreshEntity(new ItemEntity( (ServerLevel)level, pos.getX(), pos.getY(), pos.getZ(), drop));
                }
                event.setCanceled(true);
            }
        }
    }

}
