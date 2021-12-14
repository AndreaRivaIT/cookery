package it.unimib.cookery.models;

import android.util.Log;

import java.util.ArrayList;


/*creata la classe della ricetta sia per il frgamnt delle ricette dell'utente, e ci servirà in seguito */
public class Recipe {

    private String name;
    // imageId andrà sostituito con una stringa per l'url dell'immagine
    private String imageUrl;
    private int id;
    private int imageId;
    private String category;
    private int nPerson;
    ArrayList<RecipeStep> stepsList = new ArrayList<>();
    ArrayList<Ingredient> ingredientList= new ArrayList<>();

    // serve per prova
    public Recipe() {
    }


    public Recipe(int id, String imageUrl, String name){
        this.id =id;
        this.imageUrl = imageUrl;
        this.name = name;
    }



    public Recipe(String name, String category, int imageId) {
        this.name = name;
        this.category = category;
        this.imageId = imageId;
        this.nPerson = 2;
    }
    public  void setnPerson(int n){
        for(int i = 0; i < ingredientList.size(); i++){
            int qBase = ingredientList.get(i).getQuantity() / nPerson;
            ingredientList.get(i).setQuantity(qBase * n);
            Log.d("test","nome:" + ingredientList.get(i).getIngredientName()+"- quantita:"+ingredientList.get(i).getQuantity());
        }
        nPerson = n;
    }

    public ArrayList<RecipeStep> getStepsList() {
        return stepsList;
    }



    public ArrayList<Ingredient> getIngredientList() {

        return ingredientList;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id=" + id +
                '}';
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }


    public int getImageId() {
        return imageId;
    }
    public  int getnPerson(){
        return nPerson;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setIngredientList(Ingredient ingredientList) {
        this.ingredientList.add(ingredientList);
    }

    public void setStepsList(RecipeStep stepsList) {

        this.stepsList.add(stepsList);
    }


}
