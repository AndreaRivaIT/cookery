package it.unimib.cookery.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class Recipe {
    private String name;
    private int imgId;
    private int nPerson;
    ArrayList<RecipeStep> stepsList = new ArrayList<>();
    ArrayList<Ingredient> ingredientList= new ArrayList<>();

    public  void setnPerson(int n){

        for(int i = 0; i < ingredientList.size(); i++){
           int qBase = ingredientList.get(i).getQuantity() / nPerson;
           ingredientList.get(i).setQuantity(qBase * n);
           Log.d("test","nome:" + ingredientList.get(i).getIngredientName()+"- quantita:"+ingredientList.get(i).getQuantity());
        }
        nPerson = n;
    }

    public void setStepsList(RecipeStep stepsList) {

        this.stepsList.add(stepsList);
    }

    public ArrayList<RecipeStep> getStepsList() {

        return stepsList;
    }

    public void setIngredientList(Ingredient ingredientList) {
        this.ingredientList.add(ingredientList);
    }

    public ArrayList<Ingredient> getIngredientList() {

        return ingredientList;
    }

    public Recipe(String name, int imgId){
        this.name = name;
        this.imgId = imgId;
        this.nPerson = 2;
    }



    public String getName(){

        return name;
    }
    public int getImgId(){

        return imgId;
    }

    public  int getnPerson(){
        return nPerson;
    }
}
