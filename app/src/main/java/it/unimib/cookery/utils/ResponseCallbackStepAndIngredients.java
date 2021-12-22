package it.unimib.cookery.utils;

import java.util.List;

import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.StepApi;

public interface ResponseCallbackStepAndIngredients {


    void onResponseRecipeSteps(List<StepApi> steps);
    void onResponseRecipeIngredients(List<IngredientApi> ingredients, int servings);
    void onFailureIngredientAndStep(int errorMessage);


}
