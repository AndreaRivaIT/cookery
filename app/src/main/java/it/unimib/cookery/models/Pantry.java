package it.unimib.cookery.models;

import java.util.ArrayList;

public class Pantry {

     ArrayList<Ingredient> ingredientList = new ArrayList<>();

     public Pantry(ArrayList<Ingredient> ingredientList) {
          this.ingredientList = ingredientList;
     }

     public Pantry(Ingredient ingredient) {
          this.ingredientList.add(ingredient);
     }

     public ArrayList<Ingredient> getIngredientList() {
          return ingredientList;
     }

     public void setIngredientList(ArrayList<Ingredient> ingredientList) {
          this.ingredientList = ingredientList;
     }
     public void setIngredient(Ingredient ingredient) {
          this.ingredientList.add(ingredient);
     }

}
