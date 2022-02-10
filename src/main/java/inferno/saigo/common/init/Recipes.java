package inferno.saigo.common.init;

import inferno.saigo.common.recipes.Recipe;
import java.util.HashMap;

public class Recipes {
    public static final HashMap<String, Recipe> RECIPE_MAP = new HashMap<>();

    public static void init(){}

    public static void register(Recipe... recipes){
        for (Recipe recipe: recipes) {
            register(recipe);
        }
    }

    public static void register(Recipe recipe){
        RECIPE_MAP.put(recipe.getName(), recipe);
    }
}
