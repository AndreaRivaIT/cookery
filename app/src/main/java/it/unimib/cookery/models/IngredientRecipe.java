package it.unimib.cookery.models;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "ingredient_pantry")
public class IngredientRecipe extends IngredientApi {

    
    public IngredientRecipe(int id, String name) {
        super(id, name);

    }
}