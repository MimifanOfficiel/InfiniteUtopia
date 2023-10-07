package fr.codecrafters.infiniteutopia.item.elements;

import lombok.Getter;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

@Getter
public class AtomItem extends Item {

    @NotNull
    private final String identifier;

    private final int atomicNumber;

    private final double atomicMass;

    private final double meltingPoint;

    private final double boilingPoint;

    private final double density;

    public AtomItem(@NotNull String identifier, int atomicNumber, double atomicMass, double meltingPoint, double boilingPoint, double density) {
        super(new Item.Properties());

        this.identifier = identifier;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
        this.density = density;
    }

}
