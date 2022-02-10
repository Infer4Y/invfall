package inferno.saigo.common.recipes;


import inferno.saigo.common.items.ItemStack;

import java.util.List;

public class Recipe {
    private final List<ItemStack> ingredients;
    private final List<ItemStack> result;
    private final String name;

    public Recipe(String name, List<ItemStack> ingredients, List<ItemStack> result) {
        this.ingredients = ingredients;
        this.result = result;
        this.name = name;
    }

    public List<ItemStack> getIngredients() {
        return ingredients;
    }

    public List<ItemStack> getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public boolean isCraftable(){
        return false;
    }

    public void craft() { }
}
