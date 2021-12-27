package it.unimib.cookery.repository;

import android.app.Application;
import android.util.Log;

import java.util.List;

import it.unimib.cookery.R;
import it.unimib.cookery.costants.Costants;
import it.unimib.cookery.database.RecipeDao;
import it.unimib.cookery.database.RoomDatabase;
import it.unimib.cookery.database.StepDao;
import it.unimib.cookery.models.Ingredient;
import it.unimib.cookery.models.IngredientApi;
import it.unimib.cookery.models.IngredientPantry;
import it.unimib.cookery.models.Pantry;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.models.RecipeApi;
import it.unimib.cookery.models.RecipeStep;
import it.unimib.cookery.models.ResponseRecipe;
import it.unimib.cookery.service.SpoonacularApiService;
import it.unimib.cookery.utils.ResponseCallbackApi;
import it.unimib.cookery.utils.ResponseCallbackDb;
import it.unimib.cookery.utils.ServiceLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private SpoonacularApiService spoonacularApiService;
    private ResponseCallbackApi responseCallback;
    private List<RecipeApi> RecipeApiList;
    private List<RecipeApi> RecipeApiListDessert;
    private List<RecipeApi> RecipeApiListMainCourse;
    private List<RecipeApi> RecipeApiListFirstCourse;


    private Costants costants = new Costants();

    //db variable
    private Application mApplication;
    private RecipeDao mRecipeDao;
    private StepDao mStepDao;
    private ResponseCallbackDb mResponseCallbackDb;

    public RecipeRepository(Application application, ResponseCallbackApi responseCallback) {
        this.responseCallback = responseCallback;
        this.spoonacularApiService = ServiceLocator.getInstance().getSpoonacularApiService();
        //code for db
        this.mApplication = application;
        RoomDatabase roomDatabase = ServiceLocator.getInstance().getDao(application);
        this.mRecipeDao = roomDatabase.recipeDao();
        this.mStepDao = roomDatabase.recipeStepDao();
    }

    public RecipeRepository(Application application, ResponseCallbackDb responseCallback) {
        this.spoonacularApiService = ServiceLocator.getInstance().getSpoonacularApiService();
        //code for db
        this.mApplication = application;
        RoomDatabase roomDatabase = ServiceLocator.getInstance().getDao(application);
        this.mRecipeDao = roomDatabase.recipeDao();
        this.mStepDao = roomDatabase.recipeStepDao();
        this.mResponseCallbackDb = responseCallback;
    }


    public void getRandomRecipeDessert(String tags) {

        String allTags = tags + ",dessert";
        Call<ResponseRecipe> RandomRecipeDessert;
        if (tags.equals("")) {
            RandomRecipeDessert =
                    spoonacularApiService.getRandomRecipe(costants.API_KEY, 10, "dessert");
        } else {
            RandomRecipeDessert =
                    spoonacularApiService.getRandomRecipe(costants.API_KEY, 10, allTags);
        }
        RandomRecipeDessert.enqueue(new Callback<ResponseRecipe>() {  // con enqueue è asincrona con execute è sincrona
            @Override
            public void onResponse(Call<ResponseRecipe> call, Response<ResponseRecipe> response) {
                //  Log.d("retrofit", "" + response.raw().request().url());
                if (response.body() != null && response.isSuccessful()) {
                    RecipeApiListDessert = response.body().getRecipes();
                    responseCallback.onResponseRandomRecipeDessert(RecipeApiListDessert);
                } else
                    responseCallback.onFailure(R.string.errorRetriveData);
            }
            @Override
            public void onFailure(Call<ResponseRecipe> call, Throwable t) {
                Log.d("retrofit", "on Failure " + t);
                responseCallback.onFailure(R.string.connectionError);
            }
        });
    }
    public void getRandomRecipeMainCourse(String tags) {

        String allTags = tags + ",main course";
        Call<ResponseRecipe> RandomRecipeMainCourse;

        if (tags.equals("")) {
            RandomRecipeMainCourse =
                    spoonacularApiService.getRandomRecipe(costants.API_KEY, 10, "main course");
        } else {
            RandomRecipeMainCourse =
                    spoonacularApiService.getRandomRecipe(costants.API_KEY, 10, allTags);
        }

        RandomRecipeMainCourse.enqueue(new Callback<ResponseRecipe>() {
            @Override
            public void onResponse(Call<ResponseRecipe> call, Response<ResponseRecipe> response) {

                //  Log.d("retrofit", "" + response.raw().request().url());

                if (response.body() != null && response.isSuccessful()) {
                    RecipeApiListMainCourse = response.body().getRecipes();
                    responseCallback.onResponseRandomRecipeMainCourse(RecipeApiListMainCourse);
                } else
                    responseCallback.onFailure(R.string.errorRetriveData);
            }
            @Override
            public void onFailure(Call<ResponseRecipe> call, Throwable t) {

                Log.d("retrofit", "on Failure " + t);

                responseCallback.onFailure(R.string.connectionError);
            }
        });


    }
    public void getRandomRecipeFirstCourse(String tags) {


        String allTags = tags + ",side dish";
        Call<ResponseRecipe> RandomRecipeFirstCourse;

        if (tags.equals("")) {
            RandomRecipeFirstCourse =
                    spoonacularApiService.getRandomRecipe(costants.API_KEY, 10, "side dish");
        } else {
            RandomRecipeFirstCourse =
                    spoonacularApiService.getRandomRecipe(costants.API_KEY, 10, allTags);
        }


        RandomRecipeFirstCourse.enqueue(new Callback<ResponseRecipe>() {
            @Override
            public void onResponse(Call<ResponseRecipe> call, Response<ResponseRecipe> response) {

                Log.d("lll", "" + response.raw().request().url());

                if (response.body() != null && response.isSuccessful()) {
                    RecipeApiListFirstCourse = response.body().getRecipes();
                    responseCallback.onResponseRandomRecipeFirstCourse(RecipeApiListFirstCourse);
                } else
                    responseCallback.onFailure(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<ResponseRecipe> call, Throwable t) {

                Log.d("retrofit", "on Failure " + t);

                responseCallback.onFailure(R.string.connectionError);
            }
        });


    }
    public void getRandomRecipe(String tags) {

        Call<ResponseRecipe> RandomRecipe;

        if (tags.equals("")) {
            RandomRecipe =
                    spoonacularApiService.getRandomRecipeNoTags(costants.API_KEY, 10);
        } else {
            RandomRecipe =
                    spoonacularApiService.getRandomRecipe(costants.API_KEY, 10, tags);
        }


        RandomRecipe.enqueue(new Callback<ResponseRecipe>() {
            @Override
            public void onResponse(Call<ResponseRecipe> call, Response<ResponseRecipe> response) {

                //Log.d("body", ""+response.raw().request().url());
                //Log.d("body", "" + response.body());


                // non scarica l'intero json ma solo un pezzo e quindi a
                // volte perde delle ricette

                if (response.body() != null && response.isSuccessful()) {

                    RecipeApiList = response.body().getRecipes();

                    responseCallback.onResponseRandomRecipe(RecipeApiList);
                } else
                    responseCallback.onFailure(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<ResponseRecipe> call, Throwable t) {

                Log.d("retrofit", "on Failure " + t);

                responseCallback.onFailure(R.string.connectionError);
            }
        });


    }

/*
    public void getRecipeInfo() {


        Call<String> RecipeDetails =
                spoonacularApiService.getRecipeDetails(324694, costants.API_KEY);

        RecipeDetails.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body() != null && response.isSuccessful())
                    // Log.d("retrofit", ""+response.raw().request().url());
                    //  Log.d("retrofit", ""+response.body());
                    responseCallback.onResponseGetStep(response.body());
                else
                    responseCallback.onFailure(R.string.errorRetriveData);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                responseCallback.onFailure(R.string.connectionError);
            }
        });

    }*/

    public void createRecipe(Object obj){
    saveObj(obj);
}

    private void saveObj(Object obj){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(obj instanceof Recipe) {
                    mRecipeDao.insertRecipe((Recipe) obj);
                }
                else if(obj instanceof RecipeStep) {
                    mStepDao.insertStep((RecipeStep) obj);
                }
                else if(obj instanceof IngredientApi) {
                   // mStepDao.insertStep((IngredientApi) obj);
                }
                //responseCallback.onResponse(mPantryDao.pantryWithIngredientPantry(1));
            }
        };
        new Thread(runnable).start();

    }

    public void readAllRecipe(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mResponseCallbackDb.onResponse(mRecipeDao.getAll());
            }
        };
        new Thread(runnable).start();

    }
}
