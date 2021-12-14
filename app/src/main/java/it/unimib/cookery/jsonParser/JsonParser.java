package it.unimib.cookery.jsonParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {


    // mi serve per creare un oggetto parser nel main su cui poi richiamare
    public JsonParser() {
    }

    // metodo che serve per creare il file jason data la stringa passata dalla main activity
    private static JSONObject getJsonObject(String jsonFile) {
        try {
            JSONObject obj = new JSONObject(jsonFile);
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    // dovrebbe ritornare una ricetta
    private static void extractInfo(JSONObject obj) {
        int RecipeId = 0;
        String RecipeImgUrl = "";
        String RecipeName = "";


        try {
            // setto l'id
            RecipeId = obj.getInt("id");

            // setto il titolo
            RecipeName = obj.getString("title");

            // setti l'img URl
            RecipeImgUrl = obj.getString("image");


            Log.d("print", " " + RecipeId);
            Log.d("print", " " + RecipeName);
            Log.d("print", " " + RecipeImgUrl);

            // return Recipe(.....)
        } catch (Exception e) {

        }
    }

    // ritorna un arrayList di ricetta
    public static void parseRandomRecipe(String jsonFile) {

        // Arraylist <Recipe> RandomRecipe = new Arraylist<>();

        try {
            JSONObject obj = getJsonObject(jsonFile);

            JSONArray arr = obj.getJSONArray("recipes");

            for (int i = 0; i < arr.length(); i++) {


                //RandomRecipe.add(extractInfo(arr.getJSONObject(i));
                // ottengo id, nome e url dell'immagine dal metodo sopra
                extractInfo(arr.getJSONObject(i));

            }
        } catch (Exception e) {

        }

        // return RandomRecipe
    }

    /* -- fine parser per ricette randomiche -- */














}
