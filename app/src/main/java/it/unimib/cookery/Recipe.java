package it.unimib.cookery;


import java.util.ArrayList;




/*creata la classe della ricetta sia per il frgamnt delle ricette dell'utente, e ci servirà in seguito */
public class Recipe {

    private String name;
    private int imgId;
    private String category;



   // serve per prova
   public Recipe(){}

    public Recipe(String name, String category, int imgId){
        this.name=name;
        this.category=category;
        this.imgId=imgId;
    }


    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public int getImgId(){
        return imgId;
    }


}
