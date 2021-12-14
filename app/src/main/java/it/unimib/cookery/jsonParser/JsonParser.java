package it.unimib.cookery.jsonParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import it.unimib.cookery.models.Recipe;

public class JsonParser {


    // mi serve per creare un oggetto parser nel main su cui poi richiamare
    public JsonParser() {
    }

    // metodo che serve per creare il file jason data la stringa passata dalla main activity
    private static synchronized JSONObject getJsonObject(String jsonFile) {
        try {
            JSONObject obj = new JSONObject(jsonFile);
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    // dovrebbe ritornare una ricetta
    private static synchronized Recipe extractInfo(JSONObject obj) {
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


           Recipe r = new Recipe(RecipeId, RecipeImgUrl, RecipeName);

            Log.d("stampar", ""+r.toString());

            return r;

            // return Recipe(.....)
        } catch (Exception e) {

        }
        return null;
    }

    // ritorna un arrayList di ricetta
    public static synchronized ArrayList<Recipe> parseRandomRecipe(String jsonFile) {

         ArrayList <Recipe> RandomRecipe = new ArrayList<>();

        try {
            JSONObject obj = getJsonObject(jsonFile);

            JSONArray arr = obj.getJSONArray("recipes");

            for (int i = 0; i < arr.length(); i++) {


                //RandomRecipe.add(extractInfo(arr.getJSONObject(i));
                // ottengo id, nome e url dell'immagine dal metodo sopra
                RandomRecipe.add(extractInfo(arr.getJSONObject(i)));

            }

            return RandomRecipe;

        } catch (Exception e) {
             return null;
        }

        // return RandomRecipe
    }

    /* -- fine parser per ricette randomiche -- */














}
