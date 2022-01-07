package it.unimib.cookery.costants;

public class Costants {


    /* inizio stringhe costanti Alimentar Prefences Fragment */

    public static final String GLUTEN = "Gluten";
    public static final String LACTOSE = "Lactose";
    public static final String NUTS = "Nuts";
    public static final String VEGAN = "Vegan";
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

    public static final String RECIPE_NAME = "Recipe name";
    public static final String TYPE = "Type";
    public static final String RECIPE_ID = "Recipe id";
    public static final String RECIPE_IMAGE = "Recipe image";
    public static final String STEP_ARRAYLIST = "StepArraylist";
    public static final String INGREDIENT_ARRAYLIST = "IngredientArraylist";
    public static final String RECIPE_SERVINGS = "Servings";
    public static final String MISSING_INGREDIENTS="missingIngredients";
    public static final String READY_TO_COOCK="readyToCoock";
    public static final String OTHER="other";


    /* stringhe costanti per intent */

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "dbCookery";

    /* fine stringhe costanti per intent */

    /* stringhe costanti  retrofit */
    //  8b2514bd6d21482e8b4330863ee719b8
    private static final String myApiKey = "dabd7cf0ddb04d2dbfe85a7597c4067b";
    private static final String genericApiKey = "c1ded2413e85406984a2cfc38bdac50a";

    public static final String API_KEY = myApiKey;
    public static final String BASE_URL = "https://api.spoonacular.com/";

    /* fine stringhe costanti per reftrofit */


    /* stringhe per singleRecipeActivity */

    public static final String PEOPLE = " People";
    public static final String PERSON = " Person";

    /* stringhe per singleRecipeActivity */


    /* stringhe main activity */

    public static final String SHARED_PREFERENCES_FILE_NAME ="sharedPreferences";
    public static final String FIRST_ACCESS="firstAccess";
    public static final String CSV_FILE_NAME="ingredient.csv";

    /* fine stringhe main activity */

    public Costants() {

    }


}
