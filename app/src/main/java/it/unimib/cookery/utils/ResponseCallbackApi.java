package it.unimib.cookery.utils;

public interface ResponseCallbackApi {

    void onResponseRandomRecipe(String jsonFile);
    void onResponseRandomRecipeDessert(String jsonFile);
    void onResponseRandomRecipeMainCourse(String jsonFile);
    void onResponseRandomRecipeFirstCourse(String jsonFile);
    void onResponseGetStep(String jsonFile);
    void onFailure(int errorMessage);
}
