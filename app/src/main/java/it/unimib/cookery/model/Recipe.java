package it.unimib.cookery.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Recipe {
    private String name;
    private int imgId;
    private Ingredient[] ingredients = new Ingredient[5];
    private RecipeStep[] steps = new RecipeStep[5];

    ArrayList<RecipeStep> stepsList = new ArrayList<>();

    public void setStepsList(RecipeStep stepsList) {
        this.stepsList.add(stepsList);
    }

    public ArrayList<RecipeStep> getStepsList() {
        return stepsList;
    }

    public Recipe(String name, int imgId){
        this.name = name;
        this.imgId = imgId;
    }

    //aggiungi ingrediente alla ricetta
    public boolean addIngredient(Ingredient ingredient) {
        if (ingredient == null)
            return false;
        //aggiungi ingrediente all'ultima voce dell'array
        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i] == null) {
                ingredients[i] = ingredient;
                return true;
            }
        }
        return false;
    }

    //aggiungi step alla ricetta
    public boolean addStep(RecipeStep recipeStep) {
        if (recipeStep == null)
            return false;
        //aggiungi ingrediente all'ultima voce dell'array
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] == null) {
                steps[i] = recipeStep;
                return true;
            }
        }
        return false;
    }

    public String getName(){
        return name;
    }
    public int getImgId(){
        return imgId;
    }
    public  Ingredient[] getIngredients(){
        return ingredients;
    }
    public  RecipeStep[] getSteps(){
        return steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name = '" + name + '\'' +
                ", imgId= " + imgId +
                ", ingredients = " + ingredients[0].getIngredientName() +" "+ ingredients[1].getIngredientName()+" " +
                ingredients[2].getIngredientName()+" "+ingredients[3].getIngredientName()+" "+ingredients[4].getIngredientName()+
                '}';
    }
    public String toStringRecipe(){
        return"Steps: "+steps[0].getDescription() +" "+steps[1].getDescription() +" "+steps[2].getDescription() +" "+
                steps[3].getDescription() +" "+steps[4].getDescription() +" ";
    }
}
