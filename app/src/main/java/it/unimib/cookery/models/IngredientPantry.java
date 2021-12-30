package it.unimib.cookery.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_pantry")
public class IngredientPantry extends Ingredient{

    @PrimaryKey(autoGenerate = true)
    private int idIngredientPantry;
    private int data;
    private String measureUnit;
    public long pantryId;

    @Ignore
    public IngredientPantry(int idIngredient, String ingredientName, int quantity) {
        super(idIngredient, ingredientName, quantity);
    }

    public IngredientPantry(int idIngredient, String ingredientName, int quantity, int data, long pantryId, String measureUnit) {
        super(idIngredient, ingredientName, quantity);
        this.data = data;
        this.pantryId = pantryId;
        this.measureUnit = measureUnit;
    }


    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUneit) {
        this.measureUnit = measureUneit;
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
