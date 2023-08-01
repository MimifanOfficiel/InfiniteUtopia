package fr.codecrafters.infiniteutopia.item.food;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Consumable extends Item {

    private int nutrition;
    private float saturation;

    public Consumable(int nutrition, float saturation) {
        super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(nutrition).saturationMod(saturation).build()));

        this.nutrition = nutrition;
        this.saturation = saturation;
    }
}
