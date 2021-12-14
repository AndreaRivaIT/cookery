package it.unimib.cookery.service;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface SpoonacularApiService {

    @GET("random")
    Call<String> getRandomRecipeString (@Query("apiKey") String apiKey, @Query("number") int number, @Query("tags") String tags);

    @GET("random")
    Call<String> getRandomRecipeStringNoTags (@Query("apiKey") String apiKey, @Query("number") int number);

// https://api.spoonacular.com/recipes/324694/analyzedInstructions

    @GET("{id}/analyzedInstructions")
    Call<String> getRecipeDetails (@Path("id") int id, @Query("apiKey") String apiKey);
}
