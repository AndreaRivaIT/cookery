package it.unimib.cookery.repository;

import android.util.Log;

import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.service.SpoonacularApiService;
import it.unimib.cookery.utils.ResponseCallbackApi;
import it.unimib.cookery.utils.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private static final String ERROR_STRING = "Something went wrong, pleas check your connection";

   private SpoonacularApiService spoonacularApiService;
    private ResponseCallbackApi responseCallback;

    private Costants costants = new Costants();



    public RecipeRepository(ResponseCallbackApi responseCallback) {

        this.responseCallback = responseCallback;
        this.spoonacularApiService = ServiceLocator.getInstance().getSpoonacularApiService();
    }


    public void getRandomRecipe() {

        Call<String> RandomRecipeString =
                spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, "dessert");


        RandomRecipeString.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                //  Log.d("retrofit", "" + response.raw().request().url());

                if(response.body() != null && response.isSuccessful())
                    responseCallback.onResponseRandomRecipe(response.body());
                else
                    responseCallback.onFailure("Error in retrieving data");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("retrofit", "on Failure "+ t);

                responseCallback.onFailure(ERROR_STRING);
            }
        });


    }


    public void getRecipeInfo() {


        Call<String> RecipeDetails =
                spoonacularApiService.getRecipeDetails(324694, costants.API_KEY);

        RecipeDetails.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body() != null && response.isSuccessful())
                    // Log.d("retrofit", ""+response.raw().request().url());
                    //  Log.d("retrofit", ""+response.body());
                    responseCallback.onResponseGetStep(response.body());
                else
                    responseCallback.onFailure("Error in retrieving data");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                responseCallback.onFailure(ERROR_STRING);
            }
        });

    }



}
