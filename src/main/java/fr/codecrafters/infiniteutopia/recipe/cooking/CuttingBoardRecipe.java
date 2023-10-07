package fr.codecrafters.infiniteutopia.recipe.cooking;

import com.google.gson.JsonObject;
import fr.codecrafters.infiniteutopia.InfiniteUtopia;
import lombok.Getter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CuttingBoardRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;

    private final ItemStack output;

    @Getter
    private final Ingredient tool;

    @Getter
    private final Ingredient input;

    public CuttingBoardRecipe(ResourceLocation id, ItemStack output, Ingredient tool, Ingredient input) {
        this.id = id;
        this.output = output;
        this.input = input;
        this.tool = tool;
    }

    @Override
    public boolean matches(@NotNull SimpleContainer pContainer, @NotNull Level pLevel) {
        return this.input.test(pContainer.getItem(0))
                && tool.test(pContainer.getItem(1));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer pContainer, @NotNull RegistryAccess pRegistryAccess) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess pRegistryAccess) {
        return this.getResultItem();
    }

    public @NotNull ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CuttingBoardRecipe> {
        private Type() {}
        public static final CuttingBoardRecipe.Type INSTANCE = new CuttingBoardRecipe.Type();
        public static final String ID = "cutting_board";
    }

    public static class Serializer implements RecipeSerializer<CuttingBoardRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(InfiniteUtopia.MOD_ID,"cutting_board");

        @Override
        public @NotNull CuttingBoardRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            Ingredient tool = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "tool"));
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input"));

            return new CuttingBoardRecipe(id, output, tool, input);
        }

        @Override
        public CuttingBoardRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);
            Ingredient tool = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();

            return new CuttingBoardRecipe(id, output, tool, input);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buf, CuttingBoardRecipe recipe) {
            recipe.getInput().toNetwork(buf);
            recipe.getTool().toNetwork(buf);
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
