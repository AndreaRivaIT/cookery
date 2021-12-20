package it.unimib.cookery.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_pantry")
public class IngredientPantry extends Ingredient{

    @PrimaryKey(autoGenerate = true)
    private int idIngredientPantry;
    private int data;
    public long pantryId;

    @Ignore
    public IngredientPantry(int idIngredient, String ingredientName, int quantity) {
        super(idIngredient, ingredientName, quantity);
    }

    public IngredientPantry(int idIngredient, String ingredientName, int quantity, int data, long pantryId) {
        super(idIngredient, ingredientName, quantity);
        this.data = data;
        this.pantryId = pantryId;
    }

    public int getIdIngredientPantry() {
        return idIngredientPantry;
    }

    public void setIdIngredientPantry(int idIngredientPantry) {
        this.idIngredientPantry = idIngredientPantry;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
}
