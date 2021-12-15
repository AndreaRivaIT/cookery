package it.unimib.cookery.costants;

public class Costants {


    /* inizio stringhe costanti Alimentar Prefences Fragment */

    public static final String GLUTEN = "Gluten";
    public static final String LACTOSE = "Lactose";
    public static final String NUTS = "Nuts";
    public static final String VEGAN= "Vegan";
    public static final String VEGETARIAN = "Vegetarian";
    public static final String PESCETARIAN = "Pescetarian";


    /* fine  stringhe costanti Alimentar Prefences Fragment */



    /* inizio stringhe costanti My Recipes Fragment */

    public static final String FILTER0 = "Appetizers";
    public static final String FILTER1 = "First course";
    public static final String FILTER2 = "Main meal";
    public static final String FILTER3 = "Side dish";
    public static final String FILTER4 = "Desserts";
    public static final String dialogTitle = "Select recipe type";

    /* fine stringhe costanti My Recipes Fragment */




    /* stringhe tag per intent */

    public static final String RECIPE_NAME="Recipe name";
    public static final String EDITABLE="editable";
    public static final String RECIPE_ID="Recipe id";
    public static final String RECIPE_IMAGE="Recipe image";


    /* stringhe costanti per intent */

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME ="dbCookery";


    /* stringhe costanti  retrofit */

   private static final String genericApiKey= "8b2514bd6d21482e8b4330863ee719b8";
   private static final String myApiKey = "dabd7cf0ddb04d2dbfe85a7597c4067b";

    public static final String API_KEY=myApiKey;
    public static final String BASE_URL="https://api.spoonacular.com/recipes/";



    public Costants(){

    }


}
