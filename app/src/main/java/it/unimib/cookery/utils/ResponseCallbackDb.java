package it.unimib.cookery.utils;

import java.util.List;

import it.unimib.cookery.models.Recipe;

/**
 * Interface to send data from Repositories to Activity/Fragment.
 */
public interface ResponseCallbackDb {
    void onResponse(Recipe recipe);
    void onFailure(String errorMessage);
}
