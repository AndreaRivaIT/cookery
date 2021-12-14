package it.unimib.cookery.repository;

import android.util.Log;

import it.unimib.cookery.R;
import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.service.SpoonacularApiService;
import it.unimib.cookery.utils.ResponseCallbackApi;
import it.unimib.cookery.utils.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

   private SpoonacularApiService spoonacularApiService;
    private ResponseCallbackApi responseCallback;

    private Costants costants = new Costants();



    public RecipeRepository(ResponseCallbackApi responseCallback) {

        this.responseCallback = responseCallback;
        this.spoonacularApiService = ServiceLocator.getInstance().getSpoonacularApiService();
    }


    public void getRandomRecipeDessert(String tags) {

        String allTags = tags+",dessert";
        Call<String> RandomRecipeString;

        if(tags.equals("")){
            RandomRecipeString =
                    spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, "dessert");}
        else{
            RandomRecipeString =
                    spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, allTags);}


        RandomRecipeString.enqueue(new Callback<String>() {  // con enqueue è asincrona con execute è sincrona
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                //  Log.d("retrofit", "" + response.raw().request().url());

                if(response.body() != null && response.isSuccessful())
                    responseCallback.onResponseRandomRecipeDessert(response.body());
                else
                    responseCallback.onFailure(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("retrofit", "on Failure "+ t);

                responseCallback.onFailure(R.string.connectionError);
            }
        });


    }

    public void getRandomRecipeMainCourse(String tags) {

        String allTags = tags+",main course";
        Call<String> RandomRecipeString;

        if(tags.equals("")){
            RandomRecipeString =
                    spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, "main course");}
        else{
            RandomRecipeString =
                    spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, allTags);}


        RandomRecipeString.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                //  Log.d("retrofit", "" + response.raw().request().url());

                if(response.body() != null && response.isSuccessful())
                    responseCallback.onResponseRandomRecipeMainCourse(response.body());
                else
                    responseCallback.onFailure(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("retrofit", "on Failure "+ t);

                responseCallback.onFailure(R.string.connectionError);
            }
        });


    }

    public void getRandomRecipeFirstCourse(String tags) {



        String allTags = tags+",side dish";
        Call<String> RandomRecipeString;

        if(tags.equals("")){
            RandomRecipeString =
                    spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, "side dish");}
        else{
            RandomRecipeString =
                    spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, allTags);}




        RandomRecipeString.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                  Log.d("lll", "" + response.body());

                if(response.body() != null && response.isSuccessful())
                    responseCallback.onResponseRandomRecipeFirstCourse(response.body());
                else
                    responseCallback.onFailure(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("retrofit", "on Failure "+ t);

                responseCallback.onFailure(R.string.connectionError);
            }
        });


    }


    public void getRandomRecipe(String tags) {

        Call<String> RandomRecipeString;

       if(tags.equals("")){
         RandomRecipeString =
                spoonacularApiService.getRandomRecipeStringNoTags(costants.API_KEY, 10);}
       else{
           RandomRecipeString =
                   spoonacularApiService.getRandomRecipeString(costants.API_KEY, 10, tags);}


        RandomRecipeString.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                  Log.d("rr", "" + response.body());

                if(response.body() != null && response.isSuccessful())
                    responseCallback.onResponseRandomRecipe(response.body());
                else
                    responseCallback.onFailure(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("retrofit", "on Failure "+ t);

                responseCallback.onFailure(R.string.connectionError);
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
                    responseCallback.onFailure(R.string.errorRetriveData);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                responseCallback.onFailure(R.string.connectionError);
            }
        });

    }



}
