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


/* non ha senso metterlo qui ma è per prova */

    public ArrayList<Recipe> getArrayList(){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0)
                recipeList.add(new Recipe("pasta al forno", "primi", R.drawable.spoonacular));
            else
                recipeList.add(new Recipe("arrosto", "secondi", R.drawable.spoonacular));
        }

        recipeList.add(new Recipe("risotto", "primi", R.drawable.spoonacular));

        return  recipeList;
    }



}
