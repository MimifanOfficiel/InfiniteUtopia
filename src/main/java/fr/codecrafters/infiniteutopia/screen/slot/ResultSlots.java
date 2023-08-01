package fr.codecrafters.infiniteutopia.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ResultSlots extends SlotItemHandler {

    public ResultSlots(IItemHandler itemHandler, int index, int x, int y){
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
}
