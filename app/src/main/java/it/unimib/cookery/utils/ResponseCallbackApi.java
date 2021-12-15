package it.unimib.cookery.utils;

import java.util.List;

import it.unimib.cookery.models.RecipeApi;

public interface ResponseCallbackApi {

    void onResponseRandomRecipe(List<RecipeApi> recipes);
    void onResponseRandomRecipeDessert(List<RecipeApi> recipes);
    void onResponseRandomRecipeMainCourse(List<RecipeApi> recipes);
    void onResponseRandomRecipeFirstCourse(List<RecipeApi> recipes);
    void onResponseGetStep(String jsonFile);
    void onFailure(int errorMessage);
}
