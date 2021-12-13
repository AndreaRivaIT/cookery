package it.unimib.cookery.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

import it.unimib.cookery.R;
import it.unimib.cookery.database.RecipeDao;
import it.unimib.cookery.database.RoomDatabase;
import it.unimib.cookery.database.StepDao;
import it.unimib.cookery.models.Recipe;
import it.unimib.cookery.models.RecipeStep;
import it.unimib.cookery.utils.ResponseCallbackDb;
import it.unimib.cookery.utils.ServiceLocator;


/**
 * Repository to get the news using the API
 * provided by NewsApi.org (https://newsapi.org).
 */
public class DatabaseRepository{

    private static final String TAG = "DatabaseRepository";

    private final Application mApplication;
    private final RecipeDao mRecipeDao;
    private final StepDao mStepDao;
    //private final ResponseCallbackDb mResponseCallbackDb;

    public DatabaseRepository(Application application, ResponseCallbackDb responseCallback) {
        this.mApplication = application;
        RoomDatabase newsRoomDatabase = ServiceLocator.getInstance().getDao(application);
        this.mRecipeDao = newsRoomDatabase.recipeDao();
        this.mStepDao = newsRoomDatabase.stepDao();
        //this.mResponseCallbackDb =
    }

    //Crud

    public void create(Object obj){
        if(obj instanceof Recipe){
            Log.d("test", "è una ricetta");
            saveRecipe((Recipe) obj);
        }
        else if(obj instanceof RecipeStep){
            saveStep((RecipeStep) obj);
        }

    }
    public void read(Object obj){
        if(obj instanceof Recipe){
            Log.d("test", "è una ricetta");
            //readRecipe((Recipe) obj);
        }
        else if(obj instanceof RecipeStep){
            saveStep((RecipeStep) obj);
        }

    }
    public void update(Object obj){
        if(obj instanceof Recipe){
            Log.d("test", "è una ricetta");
            saveRecipe((Recipe) obj);
        }


    }
    public void delete(Object obj){
        if(obj instanceof Recipe){
            Log.d("test", "è una ricetta");
            saveRecipe((Recipe) obj);
        }


    }
//Create
    public void readLast() {
        Runnable runnable = new Runnable() {
            @Override
            public void  run() {
                //mResponseCallbackDb.onResponse(mNewsDao.getAll(), lastUpdate);
                mRecipeDao.lastRecipe();
            }
        };
        new Thread(runnable).start();

    }
    private void saveRecipe(Recipe ricetta){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mRecipeDao.insertAll(ricetta);
            }
        };
        new Thread(runnable).start();
    }
    private void saveStep(RecipeStep ricetta){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mStepDao.insertAll(ricetta);
            }
        };
        new Thread(runnable).start();
    }
}
