package it.unimib.cookery.repository;

import android.util.Log;

import java.util.ArrayList;

import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.service.SpoonacularApiService;
import it.unimib.cookery.utils.IngredientUnitMeasureResponseCallback;
import it.unimib.cookery.utils.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientMeasureUnitRepository {


    private static final String ERROR_STRING = "Something went wrong, pleas check your connection";

    SpoonacularApiService spoonacularApiService;
    IngredientUnitMeasureResponseCallback ingredientUnitMeasureResponseCallback;
    private ArrayList<String> possibleUnits;


    public static final String APIKEY = "dabd7cf0ddb04d2dbfe85a7597c4067b";


    public IngredientMeasureUnitRepository(IngredientUnitMeasureResponseCallback ingredientUnitMeasureResponseCallback) {
        spoonacularApiService= ServiceLocator.getInstance().getSpoonacularApiService();
        this.ingredientUnitMeasureResponseCallback = ingredientUnitMeasureResponseCallback;
    }


    public void getMeasure(int id, int idChip){

        Call<IngredientApi> IngredintUnitMeasure =
                spoonacularApiService.getIngredientMeasureUnit(id, APIKEY, 1);


        IngredintUnitMeasure.enqueue(new Callback<IngredientApi>() {
            @Override
            public void onResponse(Call<IngredientApi> call, Response<IngredientApi> response) {

                Log.d("retrofit", "" + response.raw().request().url());

                if(response.body() != null && response.isSuccessful()) {

                    possibleUnits = response.body().getPossibleUnits();

                    ingredientUnitMeasureResponseCallback.getUnitMeasureResponse(possibleUnits, idChip);
                }else
                    ingredientUnitMeasureResponseCallback.onFailure("Error in retrieving data");

            }

            @Override
            public void onFailure(Call<IngredientApi> call, Throwable t) {

                Log.d("retrofit", "on Failure "+ t);

                ingredientUnitMeasureResponseCallback.onFailure(ERROR_STRING);
            }
        });

    }


}
