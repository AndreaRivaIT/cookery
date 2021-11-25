package it.unimib.cookery.models;

import android.graphics.drawable.Drawable;

public class Recipe {

    private String name;
    private String category;
    private int imageId;

    public Recipe() {
        // default constructor
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
