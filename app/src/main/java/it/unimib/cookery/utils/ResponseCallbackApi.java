package it.unimib.cookery.utils;

public interface ResponseCallbackApi {

    void onResponseRandomRecipe(String jsonFile);
    void onResponseGetStep(String jsonFile);
    void onFailure(String errorMessage);
}
