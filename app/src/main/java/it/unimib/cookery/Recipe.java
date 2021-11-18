package it.unimib.cookery;


/*creata la classe della ricetta sia per il frgamnt delle ricette dell'utente, e ci servirà in seguito */
public class Recipe {

    private String name;
    private int imgId;


    public Recipe(String name, int imgId){
        this.name=name;
        this.imgId=imgId;
    }


    public String getName(){
        return name;
    }



    public int getImgId(){
        return imgId;
    }



}
