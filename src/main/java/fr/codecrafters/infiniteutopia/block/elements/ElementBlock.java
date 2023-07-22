package fr.codecrafters.infiniteutopia.block.elements;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ElementBlock extends Block {
    public ElementBlock() {
        super(BlockBehaviour.Properties.of());

        this.properties.liquid();
    }
}
