package it.unimib.cookery.models;

public class Ingredient {
    private int idIngredient;
    private String ingredientName;
    private int quantity;

    public Ingredient(int idIngredient, String ingredientName, int quantity) {
        this.idIngredient = idIngredient;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
