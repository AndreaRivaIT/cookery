package it.unimib.cookery.models;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity
public class IngredientPantry extends Ingredient{

    private int data;
    public long pantryId;

    @Ignore
    public IngredientPantry(int idIngredient, String ingredientName, int quantity) {
        super(idIngredient, ingredientName, quantity);
    }

    public IngredientPantry(int idIngredient, String ingredientName, int quantity, int data) {
        super(idIngredient, ingredientName, quantity);
        this.data = data;
    }


    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
}
