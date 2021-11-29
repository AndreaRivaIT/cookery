package it.unimib.cookery.models;

import java.util.ArrayList;


/*creata la classe della ricetta sia per il frgamnt delle ricette dell'utente, e ci servirà in seguito */
public class Recipe {

    private String name;
    private int imageId;
    private String category;


    // serve per prova
    public Recipe() {
    }

    public Recipe(String name, String category, int imageId) {
        this.name = name;
        this.category = category;
        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }


    public int getImageId() {
        return imageId;
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

}
