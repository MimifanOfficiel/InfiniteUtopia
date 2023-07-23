package fr.codecrafters.infiniteutopia.event;

import fr.codecrafters.infiniteutopia.enchantments.EnchantmentsManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnchantmentEventHandler {

    private static boolean hasEnchantment(Enchantment enchantment, Player player){
        return (player != null) && (EnchantmentHelper.getEnchantmentLevel(enchantment, player) > 0);
    }

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();

        if(event.getPlayer().isCreative()) return;
        if(!hasEnchantment(EnchantmentsManager.TELEKINESIS.get(), event.getPlayer())) return;

        LevelAccessor level = event.getLevel();

        if (level instanceof ServerLevel) {

            boolean isFull = false;
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
