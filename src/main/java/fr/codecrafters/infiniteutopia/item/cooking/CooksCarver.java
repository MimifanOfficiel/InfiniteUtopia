package fr.codecrafters.infiniteutopia.item.cooking;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class CooksCarver extends Item {

    public CooksCarver() {
        super(new Item.Properties().durability(128));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        //TODO CUTS FOOD ON THE CUTTING BOARD

        return super.useOn(pContext);
    }


}
