package it.unimib.cookery.utils;

import java.util.ArrayList;

public interface IngredientUnitMeasureResponseCallback {

    void onFailure(String errorMessage);
    void getUnitMeasureResponse(ArrayList<String> measureUnit, int id);


}
