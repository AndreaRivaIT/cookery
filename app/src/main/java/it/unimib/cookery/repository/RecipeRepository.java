package it.unimib.cookery.repository;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
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

import it.unimib.cookery.models.IngredientApi;

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



    public void getRecipeByIngredient(String ingredients){


        Call<List<RecipeApi>> RecipeByIngredients =
                spoonacularApiService.getRecipeByIngredient(costants.API_KEY, ingredients, 10);


        RecipeByIngredients.enqueue(new Callback<List<RecipeApi>>() {
            @Override
            public void onResponse(Call<List<RecipeApi>> call, Response<List<RecipeApi>> response) {

                Log.d("retrofit", "" + response.raw().request().url());

                if(response.body() != null && response.isSuccessful()) {

                    RecipeApiList = sort((ArrayList<RecipeApi>) response.body());


                    for(RecipeApi r: RecipeApiList)
                        Log.d("retrofit", " "+r.toString());

                    responseCallback.onResponseRecipeByIngredient(RecipeApiList);
                }else
                    responseCallback.onFailure(R.string.errorRetriveData);

            }

            @Override
            public void onFailure(Call<List<RecipeApi>> call, Throwable t) {

                Log.d("retrofit", "on Failure "+ t);

                responseCallback.onFailure(R.string.connectionError);
            }
        });


    }


    private ArrayList<RecipeApi> sort(ArrayList<RecipeApi> unsortedList){

        ArrayList<RecipeApi> sortedList = new ArrayList<>();

        int length = unsortedList.size();

        for(int i=0; i<length; i++){

            int min = 100000;
            RecipeApi rec = null;

            for(int j=0; j < unsortedList.size(); j++){
                if(unsortedList.get(j).getMissedIngredientCount() <= min) {
                    min = unsortedList.get(j).getMissedIngredientCount();
                    rec = unsortedList.get(j);

                }
            }

            unsortedList.remove(rec);
            sortedList.add(rec);

        }

        return sortedList;
    }



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
