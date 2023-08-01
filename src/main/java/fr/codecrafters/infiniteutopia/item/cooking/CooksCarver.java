package fr.codecrafters.infiniteutopia.item.cooking;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public class CooksCarver extends Item {

    public CooksCarver() {
        super(new Item.Properties().durability(128));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {

        return super.useOn(pContext);
    }


}
