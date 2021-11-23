package it.unimib.cookery.model;

public class Pantry {
     private Ingredient[] ingredients;

     public Pantry(int numIngredients) {
          ingredients = new Ingredient[numIngredients];
     }

     public Ingredient[] getIngredients() {
          return ingredients;
     }

     public void setIngredients(Ingredient[] ingredients) {
          this.ingredients = ingredients;
     }
}
