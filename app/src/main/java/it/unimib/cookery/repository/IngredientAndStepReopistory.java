package it.unimib.cookery.repository;

import android.util.Log;

import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.RecipeApi;
import it.unimib.cookery.models.StepApi;
import it.unimib.cookery.models.StepList;
import it.unimib.cookery.service.SpoonacularApiService;
import it.unimib.cookery.utils.ResponseCallbackStepAndIngredients;
import it.unimib.cookery.utils.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientAndStepReopistory {

    // da sistemare stringhe

    private SpoonacularApiService spoonacularApiService;
    private ResponseCallbackStepAndIngredients responseCallbackStepAndIngredients;
    private List<StepApi> stepApis;
    private List<IngredientApi> ingredientApis;
    private int servings;
    private Costants costants = new Costants();
    private static final String ERROR_STRING = "Something went wrong, pleas check your connection";


    public IngredientAndStepReopistory(ResponseCallbackStepAndIngredients responseCallbackStepAndIngredients) {
        this.responseCallbackStepAndIngredients = responseCallbackStepAndIngredients;
        this.spoonacularApiService = ServiceLocator.getInstance().getSpoonacularApiService();
    }



    public void getRecipeSteps(int id) {


        Call<List<StepList>> RecipeSteps =
                spoonacularApiService.getRecipeSteps(id, costants.API_KEY);

        RecipeSteps.enqueue(new Callback<List<StepList>>() {
            @Override
            public void onResponse(Call<List<StepList>> call, Response<List<StepList>> response) {

                if(response.body() != null && response.isSuccessful()) {
                    Log.d("retrofit", ""+response.raw().request().url());
                    //  Log.d("retrofit", ""+response.body());

                    for(int i=0; i< response.body().size(); i++) {
                        if(i==0)
                            stepApis = response.body().get(i).getSteps();
                        else
                            stepApis.addAll(response.body().get(i).getSteps());



                    }
                    //  Log.d("retrofit", "sssss "+stepApis.size());

                //    Log.d("retrofit", ""+stepApis.toString());

                    responseCallbackStepAndIngredients.onResponseRecipeSteps(stepApis);
                }else
                    responseCallbackStepAndIngredients.onFailureIngredientAndStep(R.string.errorRetriveData);
            }


            @Override
            public void onFailure(Call<List<StepList>> call, Throwable t) {

                responseCallbackStepAndIngredients.onFailureIngredientAndStep(R.string.connectionError);
            }
        });


    }

    public void getRecipeIngredients(int id, boolean nutrition){

        Call<RecipeApi> RecipeIngredient =
                spoonacularApiService.getRecipeIngredients(id, costants.API_KEY, nutrition);

        RecipeIngredient.enqueue(new Callback<RecipeApi>() {
            @Override
            public void onResponse(Call<RecipeApi> call, Response<RecipeApi> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Log.d("retrofit", "" + response.raw().request().url());
                    //  Log.d("retrofit", ""+response.body());

                    ingredientApis = response.body().getExtendedIngredients();
                    servings = response.body().getServings();
                    responseCallbackStepAndIngredients.onResponseRecipeIngredients(ingredientApis, servings);

                }
                //  Log.d("retrofit", "sssss "+stepApis.size());
                else
                    responseCallbackStepAndIngredients.onFailureIngredientAndStep(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<RecipeApi> call, Throwable t) {

                responseCallbackStepAndIngredients.onFailureIngredientAndStep(R.string.connectionError);
            }
        });




    }


}
