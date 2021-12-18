package it.unimib.cookery.service;


import it.unimib.cookery.models.ResponseRecipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface SpoonacularApiService {

    @GET("random")
    Call<ResponseRecipe> getRandomRecipe (@Query("apiKey") String apiKey, @Query("number") int number, @Query("tags") String tags);

    @GET("random")
    Call<ResponseRecipe> getRandomRecipeNoTags (@Query("apiKey") String apiKey, @Query("number") int number);

// https://api.spoonacular.com/recipes/324694/analyzedInstructions

   // @GET("{id}/analyzedInstructions")
   // Call<String> getRecipeDetails (@Path("id") int id, @Query("apiKey") String apiKey);
}
