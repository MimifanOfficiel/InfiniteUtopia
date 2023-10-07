package fr.codecrafters.infiniteutopia.block.crops;

import fr.codecrafters.infiniteutopia.item.ItemsManager;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class OnionBlock extends CropBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

    public OnionBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemsManager.ONION_SEEDS.get();
    }
}
